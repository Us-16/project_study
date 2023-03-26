package com.example.app.classroom.qna;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnARepository extends JpaRepository<QnA, Long> {
    Page<QnA> findByClassRoom_Id(Long c_id, Pageable pageable);
}
