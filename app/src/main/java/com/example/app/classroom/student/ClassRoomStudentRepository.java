package com.example.app.classroom.student;

import com.example.app.classroom.ClassRoom;
import com.example.app.user.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassRoomStudentRepository extends JpaRepository<ClassRoomStudent, Long> {
    List<ClassRoomStudent> findByClassRoom_Id(Long id);

    List<ClassRoomStudent> findByStudent_Id(Long id);


    @Query(value = "select * " +
            "from class_room_student " +
            "where class_room_id = :class_id " +
            "and student_id = :student_id", nativeQuery = true)
    ClassRoomStudent findByParams(@Param("class_id") Long c_id, @Param("student_id") Long s_id);
}
