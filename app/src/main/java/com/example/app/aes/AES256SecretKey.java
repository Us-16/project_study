package com.example.app.aes;

public interface AES256SecretKey {
    public final String key = "janghyorimjorimjoramBaseballSoli"; //32 letters
    public final String iv = key.substring(4, 20); // 16bytessd
}
