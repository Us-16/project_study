package com.example.app.question;

import com.example.app.answer.AnswerForm;
import com.example.app.user.teacher.Teacher;
import com.example.app.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page){
        Page<Question> paging = this.questionService.getList(page);
        //paging.getContent()-> List
        //paging.getContent().get(index) -> 개별 요소
        
        // 날짜만 따로 넘기면 될 거 같은디?

        //thymeleaf 안에서는 연산을 거의 할 수 없음 ?? -> 해봤어? -> 이게 되네? -> 거봐 해보고 이야기하라니까 아 ㅋ

        //getDayOfYear() -> 1년 중 며칠째인지 반환
        //getYear() -> 현재 몇 년인지 파악
        model.addAttribute("current_year", LocalDateTime.now().getYear());
        model.addAttribute("current_day", LocalDateTime.now().getDayOfYear());
        model.addAttribute("paging", paging);

        return "content/question/question_list";
    }

    @GetMapping(value="/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id, AnswerForm answerForm){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "content/question/question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "content/question/question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "content/question/question_form";
        }
        //SiteUser siteUser = this.userService.getUser(principal.getName());
        Teacher teacher = this.userService.getTeacher(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), teacher);
        return "redirect:/question/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Long id, Principal principal){
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "content/question/question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal, @PathVariable("id") Long id){
        if(bindingResult.hasErrors()){
            return "content/question/question_form";
        }
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Long id){
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.questionService.delete(question);

        return "redirect:/question/list";
    }
}
