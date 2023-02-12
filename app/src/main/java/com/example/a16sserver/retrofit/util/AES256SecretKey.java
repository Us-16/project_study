package com.example.a16sserver.retrofit.util;

public interface AES256SecretKey {
    public final String key = "janghyorimjorimjoramBaseballSoli"; //32 letters
    public final String iv = key.substring(4, 20); // 16byte
}
