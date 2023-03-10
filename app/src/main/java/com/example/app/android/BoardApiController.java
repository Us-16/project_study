package com.example.app.android;

import com.example.app.upload.Upload;
import com.example.app.upload.UploadService;
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
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Multipart;

import java.io.File;
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
    private final UploadService uploadService;
    private final UserService userService;
    private final AES256 aes256 = new AES256();
    private final Hex hex = new Hex();

    // 회의 이후에는 전체 POST 방식으로 변경 바랍니다. //

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

        return rc;
    }

    @PostMapping("/posts")
    public void test(@RequestBody Result result){
        //해치웠다아아아아아아아!!!! - 01.27 01:00경
        System.out.println(result);
        System.out.println(result.getUser_id());
        System.out.println(result.getUser_pw());
        //기념비적인 의미를 지니기 때문에 사용하지 않더라도 남겨둬야함 - 02.05 03:27
    }

    @PostMapping("/signup")
    public void signup_android(@RequestBody @NotNull SignupDTO signupDTO){
        log.info("SignUp : " + signupDTO.getStu_username());
        userService.createStudent(signupDTO.getStu_name(), signupDTO.getStu_username(), signupDTO.getStu_password(), signupDTO.getStu_pid1(), signupDTO.getStu_pid2(), signupDTO.getStu_email(), signupDTO.getStu_school(), signupDTO.getStu_grade());
    }

    //외부에서 설정한 url로 접속하는 순간, 이 함수가 호출된다고 생각하면 됨. 그래서 특별히 값을 받는 기능의 URL은 굳이 필요하지 않음.
    @GetMapping("/login/result/{username}/{password}")
    public HashMap<String, String> loginStudentResult(@PathVariable("username") String username, @PathVariable("password") String password) throws Exception{
        log.info("Attempted SignIn : http://localhost:8080/api/login/result/"+(password)+"/"+(username));

        //16진수 복호화
        username = hex.hexToString(username);
        password = hex.hexToString(password);

        //AES256 복호화
        username = aes256.decrypt(username);
        password = aes256.decrypt(password);


        ArrayList<String> testDTO = userService.LoginStudent(username, password);

        HashMap<String, String> message = new HashMap<>();
        final String[] sendTitle = {"id", "name", "username", "email", "check", "time"};
        for(int i=sendTitle.length-1; i>=0; i--)
            message.put(aes256.encrypt(sendTitle[i]), testDTO.get(i));


        log.info("Result SignIn : " + aes256.decrypt(testDTO.get(4)).charAt(0));

        return message;
    }

    @GetMapping("/image_test/{id}")
    Upload image_test(@PathVariable("id") Long id) throws Exception{
        Upload up = uploadService.getView(id);
        String path = up.getFilepath();
        String name = up.getFilename();

        return up;
    }

    @GetMapping("/page/{page}")
    ArrayList<Question> questionResponse(@PathVariable("page") int page){
        Page<Question> pageItem = this.questionService.getList(page-1);
        ArrayList<Question> pageToList = new ArrayList<Question>();

        for(Question item : pageItem) {
            pageToList.add(item);
        }
        return pageToList;
    }

    @GetMapping("/page/info")
    int getPageInfo(){
        return this.questionService.getList(0).getTotalPages();
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
