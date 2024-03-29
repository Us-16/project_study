package com.example.app.classroom;

import com.example.app.classroom.qna.QnAService;
import com.example.app.classroom.student.ClassRoomStudent;
import com.example.app.user.UserService;
import com.example.app.user.student.StudentRepository;
import com.example.app.user.student.StudentScoreRepository;
import com.example.app.user.student.StudentService;
import com.example.app.user.teacher.Teacher;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/classroom")
public class ClassRoomController {
    private final String classroomMain = "classroom/layout/mainPage";
    private final String classroomCreate = "classroom/layout/createPage";
    private final String classroomDetail = "classroom/layout/detail";
    private final UserService userService;
    private final ClassRoomService classRoomService;
    private final StudentService studentService;
    private final QnAService qnAService;

    @GetMapping("/main")
    public String mainPage(Model model, @RequestParam(value="page", defaultValue = "0")int page){
        Page<ClassRoom> paging = this.classRoomService.getClassroom(page);
        model.addAttribute("paging", paging);
        return classroomMain;
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String createPage(Model model){
        UUID uuid = UUID.randomUUID();

        model.addAttribute("code", uuid);
        return classroomCreate;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String writePage(@Valid ClassroomForm classroomForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors())
            return classroomCreate;
        Teacher teacher = this.userService.getTeacher(principal.getName());
        ClassRoom classRoom = classRoomService.createClass(teacher, classroomForm.getTitle(), classroomForm.getCode());

        classRoomService.registStudents(classRoom, classroomForm.getStudents());
        return String.format("redirect:/classroom/detail/" + classRoom.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){ //클래스룸 들어갔을 때
        List<ClassRoomStudent> students = classRoomService.getClassroomStudent(id);
        model.addAttribute("QnA", qnAService.getPageQna(0, id, 7)); //가장 최근 질문들만 받아내도록
        model.addAttribute("classInfo", classRoomService.getClassroom(id));
        model.addAttribute("students", students);
        return classroomDetail;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/student/{id}")
    public ModelAndView studentDetail(@RequestParam(name="class") Long c_id, @PathVariable("id")Long id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("studentInfo", userService.getStudent(id));
        mav.addObject("studentScore", studentService.getScore(id));
        mav.addObject("class", c_id);
        mav.setViewName("classroom/student/studentDetail");
        return mav;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/update/{id}")
    public ModelAndView studentUpdate(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView();
        mav.addObject("classInfo", classRoomService.getClassroom(id));
        mav.setViewName("classroom/layout/updatePage");
        return mav;
    }

    @PostMapping("/update/{id}")
    public String test(@PathVariable("id") Long id, String students){
        classRoomService.registStudents(classRoomService.getClassroom(id), students);

        return String.format("redirect:/classroom/detail/" + id);
    }

}
