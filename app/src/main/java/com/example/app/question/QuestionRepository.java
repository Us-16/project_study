package com.example.app.question;

import com.example.app.user.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findAll(Pageable pageable);
    List<Question> findAll();

    @Query(value="SELECT * from Question WHERE teacher_id = :t_id", nativeQuery = true)
    List<Question> findByTeacher(@Param("t_id")Long t_id);

    @Query(value = "SELECT filepath FROM question_image where question_id= :q_id", nativeQuery = true)
    String getFilePath(@Param("q_id")Long q_id);
}
