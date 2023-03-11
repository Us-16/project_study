package com.example.app.classroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/classroom")
public class ClassRoomController {
    private final String classroomMain = "classroom/class_base.html";

    @GetMapping("/")
    public String mainPage(){
        return classroomMain;
    }
}
