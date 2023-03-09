package com.example.app.android.access;


import com.example.app.util.AES256;
import com.example.app.util.Hex;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccessApiController {
    private final AES256 aes256 = new AES256();
    private final Hex hex = new Hex();
    private final MobileService mobileService;

    @GetMapping("/teacher/{username}/{password}")
    public HashMap<String, String> loginTeacherResult(@PathVariable("username") String username, @PathVariable("password") String password) throws Exception {
        username = hex.hexToString(username);
        password = hex.hexToString(password);
        username = aes256.decrypt(username);
        password = aes256.decrypt(password);

        ArrayList<String> result = mobileService.LoginTeacher(username, password);

        HashMap<String, String> message;
        message = new HashMap<>();

        final String[] sendTitle = {"id", "name", "username", "email", "check", "time"};
        for(int i=sendTitle.length-1; i>=0; i--)
            message.put(aes256.encrypt(sendTitle[i]), result.get(i));

        return message;
    }
}
