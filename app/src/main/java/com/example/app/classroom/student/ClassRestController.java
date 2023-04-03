package com.example.app.classroom.student;

import com.example.app.user.student.Student;
import com.example.app.user.student.StudentRepository;
import com.example.app.user.student.StudentScore;
import com.example.app.user.student.StudentScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClassRestController {
    private final StudentRepository studentRepository;
    private final StudentScoreRepository studentScoreRepository;

    @PostMapping("/student_score")
    @ResponseBody
    public List<StudentScore> test(@RequestParam(name="class") Long c_id, @RequestParam(name="student") Long s_id){
        Student student = studentRepository.findById(s_id).orElseThrow();
        System.out.println(student.getName());
        return studentScoreRepository.findByStudent(student);
    }
}
