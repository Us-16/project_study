package com.example.app.question;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @NotNull
    Page<Question> findAll(@NotNull Pageable pageable);
    @NotNull
    List<Question> findAll();
}
