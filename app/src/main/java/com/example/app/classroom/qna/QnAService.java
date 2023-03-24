package com.example.app.classroom.qna;

import com.example.app.DataNotFoundException;
import com.example.app.classroom.ClassRoom;
import com.example.app.user.student.Student;
import com.example.app.user.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QnAService {
    private final QnARepository qnARepository;
    private final StudentRepository studentRepository;

    public QnA create(QnAForm qnAForm, Student student, ClassRoom classRoom){
        QnA qnA = new QnA();
        qnA.setStudent(student);
        qnA.setTitle(qnAForm.getTitle());
        qnA.setContent(qnAForm.getContent());
        qnA.setCreateDate(LocalDateTime.now());
        qnA.setClassRoom(classRoom);
        qnARepository.save(qnA);
        return qnA;
    }

    public QnA modify(QnAForm qnAForm, Student student){
        QnA qnA = new QnA();
        qnA.setModifyDate(LocalDateTime.now());
        qnA.setTitle(qnAForm.getTitle());
        qnA.setContent(qnAForm.getContent());
        qnARepository.save(qnA);
        return qnA;
    }

    public Page<QnA> getPageQna(int page, Long c_id) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(sorts));
        return this.qnARepository.findByClassRoom_Id(c_id, pageable);
    }

    public QnA getQnA(Long q_id){
        return this.qnARepository.findById(q_id).orElseThrow(() -> new DataNotFoundException("아이디 잘 못 적은듯"));
    }
}
