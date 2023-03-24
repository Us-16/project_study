package com.example.app.classroom.qna;

import com.example.app.DataNotFoundException;
import com.example.app.classroom.ClassRoomRepository;
import com.example.app.user.student.Student;
import com.example.app.user.student.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/classroom")
@Controller
public class QnAController {
    private final ClassRoomRepository classRoomRepository;
    private final QnAService qnAService;
    private final StudentService studentService;
    @GetMapping("/qna")
    public ModelAndView QnAList(@RequestParam(name="class") Long c_id, @RequestParam(name="page") int page){
        ModelAndView mav = new ModelAndView();
        mav.addObject("classInfo", classRoomRepository.findById(c_id).orElseThrow(() -> new DataNotFoundException("학급 조회 안됨")));
        mav.addObject("QnAPage", qnAService.getPageQna(page, c_id, 20));
        mav.setViewName("classroom/QnA/list");
        return mav;
    }

    @PostMapping("/qna/createProcess")
    public String createPost(@Valid QnAForm qnAForm, Student student){
        return String.format("redirect:/classroom/qna");
    }

    @GetMapping("/qna/detail")
    public ModelAndView QnADetail(@RequestParam(name="class") Long c_id, @RequestParam(name="id") Long id){
        ModelAndView mav = new ModelAndView();

        mav.addObject("classInfo", classRoomRepository.findById(c_id));
        mav.addObject("QnA", qnAService.getQnA(id));

        mav.setViewName("classroom/QnA/QnADetail");
        return mav;
    }

    @GetMapping("/qna/create")
    public ModelAndView QnACreate(@RequestParam(name="class") Long c_id){
        ModelAndView mav = new ModelAndView();

        mav.setViewName("classroom/QnA/QnACreate");
        return mav;
    }

//    @PostMapping("/qna/create")
//    public void QnAPost(@RequestParam(name="class") Long c_id, @Valid QnAForm qnAForm, Principal principal){
//        System.out.print(principal.getName());
//        //Student student = this.studentService.get
//    } 왜 보류하나면, 아직 학생계정이 웹 사이트에서 어떻게 동작하게 되는지 설계를 하지 않았음;;
}
