package com.example.app;

import com.example.app.jsonplaceholderexample.PostsCallerImpl;
import com.example.app.jsonplaceholderexample.PostsRequestDto;
import com.example.app.jsonplaceholderexample.PostsResponseDto;
import com.example.app.question.QuestionService;
import com.example.app.user.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AppApplicationTests {

	@Autowired
	private PostsCallerImpl postsCaller;

	@Test
	@DisplayName("단일 포스트 조회")
	public void CallSinglePost(){
		PostsResponseDto.Posts posts = postsCaller.getPosts(30L);
	}

	@Test
	@DisplayName("포스트 리스트 조회")
	public void callPostList(){
		List<PostsResponseDto.Posts> posts = postsCaller.getAllPosts(1L);
	}

	@Test
	@DisplayName("포스트 생성")
	public void createPost(){
		PostsRequestDto.Create request = PostsRequestDto.Create.builder()
				.userId(30L)
				.title("Hello")
				.body("welcome")
				.build();
		PostsResponseDto.Create createResponse = postsCaller.createPosts(request);
	}

	@Test
	@DisplayName("포스트 생성2")
	public void createPost2(){
		PostsRequestDto.Create request = PostsRequestDto.Create.builder()
				.userId(30L)
				.title("Hello")
				.body("welcome")
				.build();
		PostsResponseDto.Create createResponse = postsCaller.createPostsByForm(request);
	}

}
