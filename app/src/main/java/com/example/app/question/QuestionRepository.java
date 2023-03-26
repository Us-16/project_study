package com.example.app.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findAll(Pageable pageable);
    List<Question> findAll();

    @Query(value = "SELECT filepath FROM question_image where question_id= :q_id", nativeQuery = true)
    String getFilePath(@Param("q_id")Long q_id);
}
