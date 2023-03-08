package com.example.app.classroom;

import com.example.app.user.student.Student;
import com.example.app.user.teacher.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@RequiredArgsConstructor
@Service
public class ClassRoomService {
    private final ClassRoomRepository classRoomRepository;

    public ClassRoom createClass(Teacher teacher, Student student, String name){
        ClassRoom classroom = new ClassRoom();

        classroom.setTeacher(teacher);
        classroom.setStudent(student);
        classroom.setName(name);
        classroom.setCreateDate(LocalDateTime.now());

        this.classRoomRepository.save(classroom);

        return classroom;
    }
}
