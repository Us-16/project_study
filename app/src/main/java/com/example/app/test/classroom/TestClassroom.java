package com.example.app.test.classroom;

import com.example.app.test.student.TestStudent;
import com.example.app.test.teacher.TestTeacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TestClassroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TestTeacher teacher;

    private String name;
}
