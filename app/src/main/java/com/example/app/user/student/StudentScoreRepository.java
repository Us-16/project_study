package com.example.app.user.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentScoreRepository extends JpaRepository<StudentScore, Long> {
    //Optional<StudentScore> findByStudent(Student student);
    List<StudentScore> findByStudent(Student student);
}
