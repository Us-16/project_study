package com.example.app.classroom.student;

import com.example.app.user.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRoomStudentRepository extends JpaRepository<ClassRoomStudent, Long> {
    List<ClassRoomStudent> findByClassRoom_Id(Long id);
}
