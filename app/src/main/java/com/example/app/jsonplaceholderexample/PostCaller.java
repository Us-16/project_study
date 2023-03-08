package com.example.app.jsonplaceholderexample;

import java.util.List;

/**
 * 진짜로 무엇을 구현할 지 적어놓은 것이라 보면 됨
 */
public interface PostCaller {
    //포스트 단일 조회
    PostsResponseDto.Posts getPosts(Long userId);
    //모든 포스트 조회
    List<PostsResponseDto.Posts> getAllPosts(Long userId);
    //포스트 저장(application/json)
    PostsResponseDto.Create createPosts(PostsRequestDto.Create create);
    //포스트 저장(application/x-www-form-urlencoded)
    PostsResponseDto.Create createPostsByForm(PostsRequestDto.Create create);
}
