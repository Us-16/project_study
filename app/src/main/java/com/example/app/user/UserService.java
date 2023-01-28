package com.example.app.user;

import com.example.app.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     *
     * @param username 닉네임
     * @param password 비밀번호
     * @param name 성함
     * @param birthday 생년월일
     * @param email 이메일
     * @param school 소속
     * @return teacher
     */
    public Teacher create(String username, String password, String name, String birthday, String email, String school){
        Teacher teacher = new Teacher();
        teacher.setUsername(username);
        teacher.setPassword(passwordEncoder.encode(password));
        teacher.setName(name);
        teacher.setBirthday(birthday);
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

    public Student create(String name, String username, String password, String P_id1, String P_id2, String email, String school, String grade){
        Student student = new Student();
        student.setName(name);
        student.setUsername(username);
        student.setPassword(password);
        student.setP_id1(P_id1);
        student.setP_id2(P_id2);
        student.setEmail(email);
        student.setSchool(school);
        student.setGrade(Integer.parseInt(grade));
        student.setCreateDate(LocalDateTime.now());
        log.info("STUDENT : " + "signup success ");
        this.studentRepository.save(student);
        return student;
    }
}
