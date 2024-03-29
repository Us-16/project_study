package com.example.app;

import com.example.app.classroom.ClassRoom;
import com.example.app.classroom.ClassRoomRepository;
import com.example.app.classroom.qna.QnAForm;
import com.example.app.classroom.qna.QnAService;
import com.example.app.question.QuestionService;
import com.example.app.user.student.Student;
import com.example.app.user.student.StudentRepository;
import com.example.app.user.student.StudentScore;
import com.example.app.user.student.StudentService;
import com.example.app.util.AES256;
import com.example.app.jsonplaceholderexample.PostsCallerImpl;
import com.example.app.jsonplaceholderexample.PostsRequestDto;
import com.example.app.jsonplaceholderexample.PostsResponseDto;
import com.example.app.user.UserService;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class AppApplicationTests {

	@Autowired
	private PostsCallerImpl postsCaller;
	@Autowired
	private UserService userService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ClassRoomRepository classRoomRepository;
	@Autowired
	private QnAService qnAService;


	AES256 aes256 = new AES256();

	@Test
	@DisplayName("얼마만큼 만들어봤어요?")
	public void makeQuestions(){
		String[] paths = {"/files/b81b24a5-9117-4b1d-853f-0264070bc47d_스크린샷 2023-02-22 오후 1.58.28.png",
				"/files/7a96f3ec-926c-4964-8fa3-9352bfcd6a1a_image0.jpg",
				"/files/tester.png"};

		for(int i=0; i<10000; i++){
			questionService.create("test_"+i, "아무 노래에 이은 아무 내용??", paths[i%3]);
			System.out.println("make question " + (i+1));
		}
	}

	@Test
	@DisplayName("CreateAccount")
	public void createStudent(){
		String[] twins = {"이천웅", "김현수", "함덕주", "박동원", "이재원",
							"박해민", "문성주", "문보경", "김기연", "손호영",
							"이정용", "고우석", "정우영", "강효종", "조원태",
							"홍창기", "진해수", "성영재", "최성훈", "송은범"};
		String email_name = "wkdgyfla";
		String email_last = "@naver.com";
		//String name = "jorim";
		for(int i=0; i<20; i++){
			userService.createStudent(twins[i], twins[i] + i, "1234", "970330", "1234567", email_name+i+email_last, "KangBuk", String.valueOf((i%3) + 1));
		}

	}
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
	@DisplayName("성적 잘 들어감?")
	public void createScore(){
		for(Long i=1L; i<21L; i++) {
			studentService.createScore(i, "한국사", (int)(50-i));
		}
	}
	@Test
	@DisplayName("성적 확인")
	public void showScore(){
		for(StudentScore score : studentService.getScore(1L)){
			System.out.println(score.getStudent().getName() + " ::: " + score.getSubject() + " ::: " + score.getScore() + " ::: " + score.getCreateDate());
		}
	}

	@Test
	@DisplayName("QnA Test")
	public void createQnA(){
		QnAForm qnAForm = new QnAForm();
		Optional<Student> student = studentRepository.findById(1L);
		Optional<ClassRoom> classRoom = classRoomRepository.findById(1L);
		for(int i=0 ;i<10; i++){
			qnAForm.setTitle("질문 있습니다!" + i);
			qnAForm.setContent("content " + i);
			qnAService.create(qnAForm, student.orElseThrow(), classRoom.orElseThrow());
		}
	}
}
