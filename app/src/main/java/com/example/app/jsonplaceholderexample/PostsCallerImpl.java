package com.example.app.jsonplaceholderexample;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostsCallerImpl implements PostCaller{
    private final RetrofitUtils retrofitUtils;
    private final PostAPI postAPI;


    @Override
    public PostsResponseDto.Posts getPosts(Long userId) {
        Call<PostsResponseDto.Posts> call = postAPI.getPosts(userId);
        return retrofitUtils.execute(call);
    }

    @Override
    public List<PostsResponseDto.Posts> getAllPosts(Long userId) {
        Call<List<PostsResponseDto.Posts>>call = postAPI.getAllPosts(userId);
        return retrofitUtils.execute(call);
    }

    @Override
    public PostsResponseDto.Create createPosts(PostsRequestDto.Create create) {
        Call<PostsResponseDto.Create> call = postAPI.createPosts(create);
        return retrofitUtils.execute(call);
    }

    @Override
    public PostsResponseDto.Create createPostsByForm(PostsRequestDto.Create create) {
        Call<PostsResponseDto.Create> call = postAPI.createPostByForm(
                create.getUserId(),
                create.getTitle(),
                create.getBody()
        );

        return retrofitUtils.execute(call);
    }
}
