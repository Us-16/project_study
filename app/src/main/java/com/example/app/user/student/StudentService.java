package com.example.app.user.student;

import com.example.app.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
     * 지금은 테스트 차원에서 id 값을 직접 넣은 것이고, 나중에는 student 객체가 들어올 수 있으니 그런 부분만 준비해주세요
     * @param id
     * @return
     */
    public StudentScore getScore(Long id){
        Optional<Student> student = studentRepository.findById(id);
        Student studentObject = student.orElseThrow(() -> new DataNotFoundException("그거 없던데?"));
        Optional<StudentScore> test =  studentScoreRepository.findByStudent(studentObject);
        return test.orElseThrow(() -> new DataNotFoundException("데이터가 없더라"));
    }
}
