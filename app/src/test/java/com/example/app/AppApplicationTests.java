package com.example.app;

import com.example.app.question.QuestionService;
import com.example.app.user.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppApplicationTests {

	@Autowired
	private QuestionService questionService;

	@Test
	void testJap(){
		for(int i=1; i<= 300; i++){
			String subject = String.format("테스트 데이터: [%03d]", i);
			String content = "테스트용입니다.";
			this.questionService.create(subject, content);
		}
	}

}
