package com.example.app.classroom;

import com.example.app.user.UserService;
import com.example.app.user.teacher.Teacher;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/classroom")
public class ClassRoomController {
    private final String classroomMain = "classroom/layout/mainPage";
    private final String classroomCreate = "classroom/layout/createPage";
    private final UserService userService;
    private final ClassRoomService classRoomService;

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

        return "redirect:/classroom/main";
    }
}
