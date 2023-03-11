package com.example.app.android.access;


import com.example.app.android.dto.LoginResultDO;
import com.example.app.util.AES256;
import com.example.app.util.Hex;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccessApiController {

    private final MobileService mobileService;
    private Hex hex = new Hex();
    private AES256 aes256 = new AES256();

    @GetMapping("/teacher/{username}/{password}")
    public LoginResultDO loginTeacherResult(@PathVariable("username") String username, @PathVariable("password") String password) throws Exception {
        System.out.println("http://localhost:8080/api/teacher/"+username + "/" + password);

        return mobileService.LoginTeacher(setDecode(username, password).get(0),setDecode(username, password).get(1));
    }
    @GetMapping("/student/{username}/{password}")
    public LoginResultDO loginStudentResult(@PathVariable("username") String username, @PathVariable("password") String password) throws Exception {
        System.out.println("http://localhost:8080/api/student/"+username + "/" + password);

        return mobileService.LoginStudent(setDecode(username, password).get(0),setDecode(username, password).get(1));
    }
    private ArrayList<String> setDecode(String username, String password) throws Exception{
        ArrayList<String> result = new ArrayList<>();
        username = hex.hexToString(username);
        password = hex.hexToString(password);
        username = aes256.decrypt(username);
        password = aes256.decrypt(password);
        result.add(username);
        result.add(password);
        return result; //is it okay?
    }
}
