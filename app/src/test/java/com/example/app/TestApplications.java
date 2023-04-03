package com.example.app;

import com.example.app.test.student.TestStudentService;
import com.example.app.test.teacher.TestTeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestApplications {
    @Autowired
    private TestStudentService studentService;
    @Autowired
    private TestTeacherService teacherService;

    @Test
    public void createStudent(){
        //studentService.create("Student1", "1234", "student");
        for(int i=2; i<6; i++){
            studentService.create("Student"+i, "1234", "student"+i);
        }
    }

    @Test
    public void createTeacher(){
        //teacherService.create("Teacher1", "1234", "teacher");
        for(int i=2; i<6; i++){
            teacherService.create("Teacher"+i, "1234", "teacher"+i);
        }
    }
}
