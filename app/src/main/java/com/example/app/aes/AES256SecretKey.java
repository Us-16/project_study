package com.example.app.aes;

public interface AES256SecretKey {
    public final String key = "janghyorimjorimjoramBaseballSoli"; //영문자 기준 32글자
    public final String iv = key.substring(4, 20); //must be 16bytes long
}
