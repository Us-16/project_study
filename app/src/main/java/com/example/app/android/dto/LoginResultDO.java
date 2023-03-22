package com.example.app.android.dto;

import lombok.Getter;

@Getter
public class LoginResultDO {
    private String id;
    private String name;
    private String username;
    private String email;
    private String check;
    private String time;

    public LoginResultDO(String id, String name, String username, String email, String check, String time) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.check = check;
        this.time = time;
    }
}
