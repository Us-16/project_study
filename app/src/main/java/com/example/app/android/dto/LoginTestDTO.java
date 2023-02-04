package com.example.app.android.dto;

import lombok.Data;

@Data
public class LoginTestDTO {
    private String id;
    private String name;
    private String username;
    private String email;
    private String check;

    public LoginTestDTO(String id, String name, String username, String email, String check) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.check = check;
    }
}
