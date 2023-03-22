package com.example.app.user;

import com.example.app.DataNotFoundException;
import com.example.app.user.student.Student;
import com.example.app.user.student.StudentRepository;
import com.example.app.user.teacher.Teacher;
import com.example.app.user.teacher.TeacherRepository;
import com.example.app.util.AES256;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AES256 aes256 = new AES256();


    /**
     * 선생님 계정 생성
     * @param username 닉네임
     * @param password 비밀번호
     * @param name 성함
     * @param personalId 주민등록번호
     * @param email 이메일
     * @param school 소속
     * @return teacher
     */
    public Teacher createTeacher(String username, String password, String name, String personalId, String email, String school){
        Teacher teacher = new Teacher();
        teacher.setUsername(username);
        teacher.setPassword(passwordEncoder.encode(password));
        teacher.setName(name);
        teacher.setPersonalId(personalId);
        teacher.setEmail(email);
        teacher.setSchool(school);
        teacher.setCreateDate(LocalDateTime.now());
        this.teacherRepository.save(teacher);
        return teacher;
    }
    public Teacher getTeacher(String username){
        Optional<Teacher> teacher = this.teacherRepository.findByUsername(username);
        if(teacher.isPresent())
            return teacher.get();
        else
            throw new DataNotFoundException("Teacher not Found");
    }
    /**
     * 학생 계정 생성
     * @param name 성명
     * @param username 아이디
     * @param password 비번
     * @param P_id1 주민등록번호 앞자리
     * @param P_id2 주민등록번호 뒷자리
     * @param email 이메일
     * @param school 학원 및 학교
     * @param grade 학년
     * @return
     */
    public Student createStudent(String name, String username, String password, String P_id1, String P_id2, String email, String school, String grade){
        String personalId = P_id1 + P_id2;
        Student student = new Student();

        student.setName(name);
        student.setUsername(username);
        student.setPassword(password);
        student.setPersonalId(personalId);
        student.setEmail(email);
        student.setSchool(school);
        student.setGrade(Integer.parseInt(grade));
        student.setCreateDate(LocalDateTime.now());
        log.info("STUDENT : " + "signup success ");
        this.studentRepository.save(student);
        return student;
    }

    public Student getStudent(Long id){
        return studentRepository.findById(id).orElseThrow(()->new DataNotFoundException("학생이 존재하지 않습니다."));
    }

    /**
     * 아이디 중복 확인
     * @param username
     * @return
     */
    public String CheckDup(String username){
        if(this.studentRepository.findByUsername(username).equals(Optional.empty())) {
            return "true";
        }
        return "false"; //check
    }

}
