package com.example.app.user;

import com.example.app.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final TeacherRepository teacherRepository;
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
}
