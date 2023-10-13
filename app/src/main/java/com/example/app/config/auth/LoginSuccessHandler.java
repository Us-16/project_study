package com.example.app.config.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final RequestCache requestCache = new HttpSessionRequestCache();
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        clearSession(request);

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String prevPage = (String)request.getSession().getAttribute("prevPage");
        if(prevPage != null)
            request.getSession().removeAttribute("prevPage");

        String uri = "/";

        if(savedRequest != null){
            uri = savedRequest.getRedirectUrl();
        }else if(prevPage != null && !prevPage.equals("")){
            if(prevPage.contains("/user/signup"))
                uri = "/";
            else
                uri = prevPage;
        }

        redirectStrategy.sendRedirect(request, response, uri);
    }

    private void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null)
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
