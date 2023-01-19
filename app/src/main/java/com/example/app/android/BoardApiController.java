package com.example.app.android;

import com.example.app.answer.Answer;
import com.example.app.answer.AnswerRepository;
import com.example.app.question.Question;
import com.example.app.question.QuestionRepository;
import com.example.app.question.QuestionService;
import com.example.app.user.SiteUser;
import com.example.app.user.UserRepository;
import com.example.app.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class BoardApiController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionService questionService;


    @GetMapping("/board")
    List<SiteUser> all(){
        return userRepository.findAll();
    }

    @GetMapping("/question")
    List<QuestionDTO> questionList() {
        List<Question> questionList = this.questionService.getList();
        List<QuestionDTO> resultList = new ArrayList<>();
        QuestionDTO questionDTO;
        for(Question q : questionList){
            questionDTO = new QuestionDTO(q);
            log.info(questionDTO.toString());
            resultList.add(questionDTO);
        }

        return resultList;
    }
}
