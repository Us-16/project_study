package com.example.app.test.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestTeacherService {
    private final TestTeacherRepository teacherRepository;

    public TestTeacher create(String username, String name, String password){
        TestTeacher teacher = new TestTeacher();
        teacher.setName(name);
        teacher.setUsername(username);
        teacher.setPassword(password);
        teacherRepository.save(teacher);
        return teacher;
    }

}
