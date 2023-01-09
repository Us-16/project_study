package com.example.app.naver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Map;

@Slf4j
@Controller
public class NaverLoginController {
    private String CLIENT_ID ="6_ziJsGEf8LuRce4lfXX";
    private String CLI_SECRET="y_ycF8RqoC";

    @RequestMapping("/naver")
    public String testNaver(HttpSession session, Model model) throws UnsupportedEncodingException, UnknownHostException {
        String redirectURI = URLEncoder.encode("http://localhost8080/naver/callback1", "UTF-8");

        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s", CLIENT_ID, redirectURI, state);
        model.addAttribute("apiURL", apiURL);

        return "test-naver";

    }

    @RequestMapping("/naver/callback1")
    public String naverCallback1(HttpSession session, HttpServletRequest request, Model model) throws IOException, ParseException, org.apache.tomcat.util.json.ParseException {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String redirectURI = URLEncoder.encode("http://localhost:8080/naver/callback1", "UTF-8");

        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id="+CLIENT_ID;
        apiURL += "&client_secret"+CLI_SECRET;
        apiURL += "&redirect_uri="+redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state="+state;

        log.info("apiURL = " + apiURL);

        String res = requestToServer(apiURL);
        if(res != null && !res.equals("")){
            model.addAttribute("res", res);
            Map<String, Object> parsedJson = new JSONParser(res).parseObject();
            log.info(parsedJson.toString());
            session.setAttribute("currentUser", res);
            session.setAttribute("currentAT", parsedJson.get("access_token"));
            session.setAttribute("currentRT", parsedJson.get("refresh_token"));
        }else{
            model.addAttribute("res", "Login_failed!");
        }
        return "test-naver-callback";
    }
    @RequestMapping("/naver/refreshToken")
    public String refreshToken(HttpSession session, HttpServletRequest request, Model model, String refreshToken) throws  IOException, ParseException{
        String apiURL;

        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
        apiURL += "client_id=" + CLIENT_ID;
        apiURL += "&client_secret" + CLI_SECRET;
        apiURL += "&refresh_token=" + refreshToken;

        log.info("apiURL"+apiURL);

        String res = requestToServer(apiURL);
        model.addAttribute("res", res);
        session.invalidate();

        return "test-naver-callback";
    }

    @RequestMapping("/naver/deleteToken")
    public String deleteToken(HttpSession session, HttpServletRequest request, Model model, String accessToken) throws IOException{
        String apiURL;

        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
        apiURL += "client_id="+CLIENT_ID;
        apiURL += "&client_secret"+CLI_SECRET;
        apiURL += "&access_token="+accessToken;
        apiURL += "&service_provider=NAVER";

        log.info("apiURL" + apiURL);

        String res = requestToServer(apiURL);
        model.addAttribute("res", res);
        session.invalidate();

        return "test-naver-callback";
    }

    @ResponseBody
    @RequestMapping("/naver/getProfile")
    public String getProfileFromNaver(String accessToken) throws IOException{
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        String headerstr = "Bearer " + accessToken;
        String res = requestToServer(apiURL, headerstr);

        return res;
    }

    @RequestMapping("/naver/invalidate")
    public String invalidateSession(HttpSession session){
        session.invalidate();
        return "redirect:/naver";
    }

    private String requestToServer(String apiURL) throws IOException{
        return requestToServer(apiURL, "");
    }

    private String requestToServer(String apiURL, String headerStr) throws IOException{
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        log.info("header Str: "+headerStr);

        if(headerStr != null && !headerStr.equals("")){
            con.setRequestProperty("Authorization", headerStr);
        }

        int responseCode = con.getResponseCode();
        BufferedReader br;

        log.info("responseCode=" + responseCode);

        if(responseCode == 200){
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        }else{
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        String inputLine;
        StringBuffer res = new StringBuffer();
        while((inputLine = br.readLine()) != null){
            res.append(inputLine);
        }
        br.close();
        if(responseCode == 200)
            return res.toString();
        else
            return null;
    }
}
