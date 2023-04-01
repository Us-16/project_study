package com.example.app.classroom.student;

import com.example.app.user.student.Student;
import com.example.app.user.student.StudentRepository;
import com.example.app.user.student.StudentScore;
import com.example.app.user.student.StudentScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClassRestController {
    private final StudentRepository studentRepository;
    private final StudentScoreRepository studentScoreRepository;

    @GetMapping("/student")
    @ResponseBody
    public List<StudentScore> test(){
        Student student = studentRepository.findById(1L).orElseThrow();
        System.out.println(student.getName());
        return studentScoreRepository.findByStudent(student);
    }
}
