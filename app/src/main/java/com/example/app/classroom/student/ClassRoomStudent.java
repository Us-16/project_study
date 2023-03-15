package com.example.app.classroom.student;

import com.example.app.classroom.ClassRoom;
import com.example.app.user.student.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ClassRoomStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "class_room_id")
    private ClassRoom classRoom;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDateTime createDate;
}
