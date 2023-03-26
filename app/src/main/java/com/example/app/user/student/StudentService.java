package com.example.app.user.student;

import com.example.app.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentScoreRepository studentScoreRepository;
    private final StudentRepository studentRepository;



    public void createScore(Long id, String subject, int score){
        Optional<Student> student = studentRepository.findById(id);
        System.out.println(student.orElseThrow().getUsername());

        StudentScore studentScore = new StudentScore();
        studentScore.setSubject(subject);
        studentScore.setStudent(student.orElseThrow());
        studentScore.setScore(score);
        studentScore.setCreateDate(LocalDateTime.now());
        studentScoreRepository.save(studentScore);
    }

    /**
     * List 반환됨
     * @param s_id
     * @return
     */
    public List<StudentScore> getScore(Long s_id){
        Optional<Student> student = studentRepository.findById(s_id);
        return studentScoreRepository.findByStudent(student.orElseThrow(() -> new DataNotFoundException("학생 조회되지 않음")));
    }
}
