package com.example.app.jsonplaceholderexample;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;

public class PostsRequestDto {
    @Getter
    public static class Create{
        //포스트 생성 시 사용
        @Builder
        public Create(Long userId, String title, String body){
            this.userId = userId;
            this.title = title;
            this.body = body;
        }
        @SerializedName("userId")
        private Long userId;
        @SerializedName("title")
        private String title;
        @SerializedName("body")
        private String body;

    }
}
