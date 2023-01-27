package com.example.app.android;

import com.example.app.question.Question;
import com.example.app.question.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class BoardApiController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question")
    List<QuestionDTO> questionList() {
        List<Question> questionList = this.questionService.getList();
        List<QuestionDTO> resultList = new ArrayList<>();
        QuestionDTO questionDTO;
        for(Question q : questionList){
            questionDTO = new QuestionDTO(q);
            resultList.add(questionDTO);
        }

        log.info("QuestionList Send");
        return resultList;
    }

//    @PostMapping("/posts")
//    public Result test(@RequestBody Result result){
//        System.out.println(result);
//        return result;
//    }

    @PostMapping("/posts")
    public void test(@RequestBody  Result result){
        //해치웠다아아아아아아아!!!!
        System.out.println(result);
        System.out.println(result.getUser_id());
        System.out.println(result.getUser_pw());
    }

}
