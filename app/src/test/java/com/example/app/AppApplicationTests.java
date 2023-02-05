package com.example.app;

import com.example.app.util.AES256;
import com.example.app.jsonplaceholderexample.PostsCallerImpl;
import com.example.app.jsonplaceholderexample.PostsRequestDto;
import com.example.app.jsonplaceholderexample.PostsResponseDto;
import com.example.app.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AppApplicationTests {

	@Autowired
	private PostsCallerImpl postsCaller;
	@Autowired
	private UserService userService;
	AES256 aes256 = new AES256();

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
	@Test
	@DisplayName("로그인 테스트")
	public void LoginTest() throws Exception {
		System.out.println(aes256.encrypt("lsd4026") + " : " + aes256.encrypt("1234"));
		ArrayList<String> test  = userService.LoginStudent(aes256.encrypt("lsd4026"), aes256.encrypt("1234"));
		test.forEach(System.out::println);
	}
}
