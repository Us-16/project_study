package com.example.app.user.student;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class StudentScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;
    private String subject;
    private int score;
    private LocalDateTime createDate;

    @Override
    public String toString() {
        return "StudentScore[" +
                "id=" + id +
                ", student=" + student.getUsername() +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                ", createDate=" + createDate +
                ']';
    }
}
