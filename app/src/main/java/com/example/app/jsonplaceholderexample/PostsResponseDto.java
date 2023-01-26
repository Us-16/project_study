package com.example.app.jsonplaceholderexample;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class PostsResponseDto {
    /**
     * post 조회할 때 사용
     */
    @ToString
    @Getter
    public static class Posts{
        @Builder
        public Posts(Long userId, Long id, String title, String body){
            this.userId = userId;
            this.id = id;
            this.title = title;
            this.body = body;
        }
        private Long userId;
        private Long id;
        private String title;
        private String body;
    }

    /**
     * 포스트 생성 응답값
     * api 요청 시, 응답값이 Json일 경우, Retrofit은 별도의 파싱 작업없이 key,value 규칙이 일치한다면 서드파트 라이브러리(Gson)를 이용해서 자동으로 파싱을 지원합니다.
     * 그래서 다른 것들에 비해 코드가 간결했던 거임!!
     */
    @ToString
    @Getter
    public static class Create{

        @Builder
        public Create(Long userId, Long id, String title, String body){
            this.userId = userId;
            this.id = id;
            this.title = title;
            this.body = body;
        }
        private Long userId;
        private Long id;
        private String title;
        private String body;
    }
}
