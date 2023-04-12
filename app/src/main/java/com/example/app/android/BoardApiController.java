package com.example.app.android;

import com.example.app.util.AES256;
import com.example.app.android.dto.*;
import com.example.app.question.Question;
import com.example.app.question.QuestionService;

import com.example.app.user.UserService;
import com.example.app.util.Hex;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {
    private final QuestionService questionService;
    private final UserService userService;
    private final AES256 aes256 = new AES256();
    private final Hex hex = new Hex();

    // 회의 이후에는 전체 POST 방식으로 변경 바랍니다. //

    @GetMapping("/question") //Retrofit 최초 사용 기념
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

    @PostMapping("/posts") //Memorial Code
    public void test(@RequestBody Result result){
        //해치웠다아아아아아아아!!!! - 01.27 01:00경
        System.out.println(result);
        System.out.println(result.getUser_id());
        System.out.println(result.getUser_pw());
        //기념비적인 의미를 지니기 때문에 사용하지 않더라도 남겨둬야함 - 02.05 03:27
    }

    @GetMapping("/check_response/{username}") //회원가입 아이디 중복 확인
    public CheckResponseDTO check_res(@PathVariable("username") String username) throws Exception{
        log.info("Duplicate Response Send");
        log.info("http://localhost:8080/api/check_response/"+username);

        String isDup = aes256.encrypt(userService.CheckDup(username));
        CheckResponseDTO rc = new CheckResponseDTO();
        rc.setMessage(isDup);

        return rc;
    }


    @PostMapping("/signup") //학생 회원가입
    public void signup_android(@RequestBody @NotNull SignupDTO signupDTO){
        log.info("SignUp : " + signupDTO.getStu_username());
        userService.createStudent(signupDTO.getStu_name(), signupDTO.getStu_username(), signupDTO.getStu_password(), signupDTO.getStu_pid1(), signupDTO.getStu_pid2(), signupDTO.getStu_email(), signupDTO.getStu_school(), signupDTO.getStu_grade());
    }

    @GetMapping("/page/{page}") //페이지 내용
    ArrayList<Question> questionResponse(@PathVariable("page") int page){
        Page<Question> pageItem = this.questionService.getList(page-1);
        ArrayList<Question> pageToList = new ArrayList<Question>();

        for(Question item : pageItem) {
            item.setFilepath(this.questionService.getFilePath(item.getId()));
            pageToList.add(item);
        }
        return pageToList;
    }

    @GetMapping("/page/info") //최대 페이지
    int getPageInfo(){
        return this.questionService.getList(0).getTotalPages();
    }

    @GetMapping("/question/{id}")
    List<Question> questionByTeacher(@PathVariable("id") Long t_id){
        return questionService.getQuestionByT_id(t_id);
    }

    public void MemTest(){
        Runtime runtime = Runtime.getRuntime();

        final NumberFormat format= NumberFormat.getInstance();

        final long maxMemory = runtime.maxMemory();
        final long allocateMemory = runtime.totalMemory();
        final long freeMemory = runtime.freeMemory();
        final long mb = 1024*1024;
        final String mega = "MB";

        log.info("======================== Memory Info ======================");
        log.info("Free Memory: " + format.format(freeMemory / mb) + mega);
        log.info("Allocated Memory: " + format.format(allocateMemory/mb) + mega);
        log.info("Max free Memory: " + format.format(maxMemory / mb) + mega);
        log.info("Total free Memory: " + format.format((freeMemory + (maxMemory - allocateMemory)) / mb) + mega);
        log.info("===========================================================");
    }
}
