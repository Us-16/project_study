package com.example.app.classroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/classroom")
public class ClassRoomController {
    private final String classroomMain = "classroom/layout/mainPage";
    private final String classroomCreate = "classroom/layout/createPage";

    @GetMapping("/main")
    public String mainPage(){
        return classroomMain;
    }

    @GetMapping("/create")
    public String createPage(){
        return classroomCreate;
    }
}
