package com.example.app.android.access;


import com.example.app.util.AES256;
import com.example.app.util.Hex;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccessApiController {
    private final AES256 aes256 = new AES256();
    private final Hex hex = new Hex();
    private final MobileService mobileService;

    @GetMapping("/teacher/{username}/{password}")
    public void loginTeacherResult(@PathVariable("username") String username, @PathVariable("password") String password) throws Exception {
        username = hex.hexToString(username);
        password = hex.hexToString(password);
        username = aes256.decrypt(username);
        password = aes256.decrypt(password);

        System.out.println("Username: " + username + " ||| Password: " + password);

        mobileService.LoginTeacher(username, password);
    }
}
