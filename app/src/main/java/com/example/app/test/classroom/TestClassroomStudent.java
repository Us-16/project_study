package com.example.app.test.classroom;

import com.example.app.test.student.TestStudent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TestClassroomStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TestClassroom classroom;
    @ManyToOne
    private TestStudent student;
}
