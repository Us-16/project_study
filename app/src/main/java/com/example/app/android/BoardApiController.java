package com.example.app.android;

import com.example.app.aes.AES256;
import com.example.app.android.dto.*;
import com.example.app.question.Question;
import com.example.app.question.QuestionService;
import com.example.app.user.Student;
import com.example.app.user.StudentRepository;
import com.example.app.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {
    private final QuestionService questionService;
    private final UserService userService;
    private final AES256 aes256 = new AES256();
    private StudentRepository studentRepository;


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

    @GetMapping("/check_response/{username}") //Post로 두니까 화면 안 띄워짐 받을때도 POST로 받으면 됨
    public CheckResponseDTO check_res(@PathVariable("username") String username) throws Exception{
        log.info("Duplicate Response Send");
        log.info("http://localhost:8080/api/check_response/"+username);

        String isDup = aes256.encrypt(userService.CheckDup(username));
        CheckResponseDTO rc = new CheckResponseDTO();
        rc.setMessage(isDup);
        MemTest();

        return rc;
    }

    @PostMapping("/posts")
    public void test(@RequestBody Result result){
        //해치웠다아아아아아아아!!!! 01.27 01:00경
        System.out.println(result);
        System.out.println(result.getUser_id());
        System.out.println(result.getUser_pw());
    }

    @PostMapping("/signup")
    public void signup_android(@RequestBody SignupDTO signupDTO){
        System.out.println(signupDTO);
        userService.createStudent(signupDTO.getStu_name(), signupDTO.getStu_username(), signupDTO.getStu_password(), signupDTO.getStu_pid1(), signupDTO.getStu_pid2(), signupDTO.getStu_email(), signupDTO.getStu_school(), signupDTO.getStu_grade());
    }

    /**
     * ID 중복처리
     * @param checkDTO JSON 파일형식
     */
    @PostMapping("/check_name")
    public void check_name(@RequestBody CheckDTO checkDTO) throws Exception {
        System.out.println(checkDTO);
        log.info("GET DATA: " + checkDTO.getUsername());
        String username = checkDTO.getUsername();
        username = aes256.decrypt(username);
        log.info(username);
        String isDup = userService.CheckDup(username);
        check_res(username);
    }

    @PostMapping("/login/test")
    public void loginTest(@RequestBody LoginDTO loginDTO) throws Exception {
        System.out.println(loginDTO.getUsername());
        loginTestResult(loginDTO.getUsername(), loginDTO.getPassword());
    }

    @GetMapping("/login/result/{password}/{username}")
    public LoginTestDTO loginTestResult(@PathVariable("username") String username, @PathVariable("password") String password) throws Exception{
        System.out.println("http://localhost:8080/api/login/result/"+password+"/"+username);
        System.out.println("why: " + username);
        System.out.println("why: " + password);
        username = aes256.decrypt(username);
        password = aes256.decrypt(password);
        System.out.println(username+" " +  password);
        ArrayList<String> testDTO = userService.LoginTest(username, password);

        LoginTestDTO lt = new LoginTestDTO(testDTO.get(0), testDTO.get(1), testDTO.get(2), testDTO.get(3), testDTO.get(4));
        return lt;
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
