package com.example.app.classroom;

import com.example.app.user.student.Student;
import com.example.app.user.teacher.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClassRoomService {
    private final ClassRoomRepository classRoomRepository;

    public ClassRoom createClass(Teacher teacher, String title, String code){
        ClassRoom classroom = new ClassRoom();
        classroom.setTeacher(teacher);
        classroom.setCode(code);
        classroom.setTitle(title);
        classroom.setCreateDate(LocalDateTime.now());

        this.classRoomRepository.save(classroom);

        return classroom;
    }

    public Page<ClassRoom> getClassroom(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(sorts));
        return this.classRoomRepository.findAll(pageable);
    }

}
