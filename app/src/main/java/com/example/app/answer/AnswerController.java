package com.example.app.answer;

import com.example.app.question.Question;
import com.example.app.question.QuestionService;
import com.example.app.user.teacher.Teacher;
import com.example.app.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal){
        Question question = this.questionService.getQuestion(id);
        Teacher teacher = this.userService.getTeacher(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "content/question/question_detail";
        }
        this.answerService.create(question, answerForm.getContent(), teacher);
        return String.format("redirect:/question/detail/%s", id);
    }
}
