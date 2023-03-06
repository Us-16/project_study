package com.example.app.question;

import com.example.app.answer.AnswerForm;
import com.example.app.question.question_image.QuestionImage;
import com.example.app.question.question_image.QuestionImageService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final UserService userService;
    private final QuestionImageService questionImageService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue = "0") int page){
        Page<Question> paging = this.questionService.getList(page);

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
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal, MultipartHttpServletRequest mtfRequest)throws Exception{
        if(bindingResult.hasErrors()){
            return "content/question/question_form";
        }
        List<MultipartFile> files = mtfRequest.getFiles("files");
        Teacher teacher = this.userService.getTeacher(principal.getName());
        Question question = this.questionService.create(questionForm.getTitle(), questionForm.getContent(), teacher);
        questionImageService.write(question, files);
        //questionImageService.create(question, "test", "test", 22222L);
        // Question 객체 만들어지면서 동시에 저장할 거고
        // 그 객체를 image에 쓰면 되는거 아님?
        // 되네 ㅇㅇ

        return "redirect:/question/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Long id, Principal principal){
        Question question = this.questionService.getQuestion(id);
        if(!question.getTeacher().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setTitle(question.getSubject());
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
        if(!question.getTeacher().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.modify(question, questionForm.getTitle(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Long id){
        Question question = this.questionService.getQuestion(id);
        if(!question.getTeacher().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.questionService.delete(question);

        return "redirect:/question/list";
    }
}
