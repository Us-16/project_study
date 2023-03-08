package com.example.app.jsonplaceholderexample;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PostAPI {
    @GET("/posts/{userId}")
    Call<PostsResponseDto.Posts> getPosts(@Path("userId") Long userId);

    @GET("/posts")
    Call<List<PostsResponseDto.Posts>> getAllPosts(@Query("userId") Long userId);

    @POST("/posts")
    Call<PostsResponseDto.Create> createPosts(@Body PostsRequestDto.Create create);
    //@body -> 파라미터 객체를 Json으로 파싱. ContentType이 application/json으로 정의됨

    @FormUrlEncoded
    @POST("/posts")
    Call<PostsResponseDto.Create> createPostByForm(
            @Field("userId") Long userId,
            @Field("title") String title,
            @Field("body") String body
    );
}
