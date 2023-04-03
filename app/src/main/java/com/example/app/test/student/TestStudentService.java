package com.example.app.test.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestStudentService {
    private final TestStudentRepository studentRepository;

    @Transactional
    public TestStudent create(String username, String password, String name){
        TestStudent student = new TestStudent();
        student.setName(name);
        student.setUsername(username);
        student.setPassword(password);
        studentRepository.save(student);
        return student;
    }
}
