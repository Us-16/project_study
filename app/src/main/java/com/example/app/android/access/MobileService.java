package com.example.app.android.access;

import com.example.app.android.dto.LoginResultDO;
import com.example.app.user.student.Student;
import com.example.app.user.student.StudentRepository;
import com.example.app.user.teacher.Teacher;
import com.example.app.user.teacher.TeacherRepository;
import com.example.app.util.AES256;
import com.example.app.util.Hex;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class MobileService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AES256 aes256 = new AES256();
    private Random random;

    private LoginResultDO send;


    public LoginResultDO LoginTeacher(String username, String password) throws Exception {
        Optional<Teacher> result = teacherRepository.findByUsername(username);
        random = new Random();
        String id="", name="", r_username="", email="", check="", time="";

        if(result.equals(Optional.empty()) || (!passwordEncoder.matches(password, result.get().getPassword()))){
            id = aes256.encrypt("id"+ (random.nextInt(1000)+1));
            name = aes256.encrypt("name"+(random.nextInt(1000)+1));
            r_username = aes256.encrypt("username"+(random.nextInt(1000)+1));
            email = aes256.encrypt("email"+(random.nextInt(1000)+1));
            check = aes256.encrypt("F-" + (random.nextInt(100) + 1));
            time = aes256.encrypt(""+ LocalDateTime.now());
        }else {
            id = aes256.encrypt("id"+ (random.nextInt(1000)+1));
            name = aes256.encrypt("name"+(random.nextInt(1000)+1));
            r_username = aes256.encrypt("username"+(random.nextInt(1000)+1));
            email = aes256.encrypt("email"+(random.nextInt(1000)+1));
            check = aes256.encrypt("T-" + (random.nextInt(100) + 1));
            time = aes256.encrypt(""+ LocalDateTime.now());
        }
        send = new LoginResultDO(id, name, r_username, email, check, time);
        return send;
    }
    public LoginResultDO LoginStudent(String username, String password) throws Exception {
        Optional<Student> result = studentRepository.findByUsername(username);
        random = new Random();
        String id="", name="", r_username="", email="", check="", time="";

        if(result.equals(Optional.empty()) || (!password.equals(result.get().getPassword()))){
            id = aes256.encrypt("id"+ (random.nextInt(1000)+1));
            name = aes256.encrypt("name"+(random.nextInt(1000)+1));
            r_username = aes256.encrypt("username"+(random.nextInt(1000)+1));
            email = aes256.encrypt("email"+(random.nextInt(1000)+1));
            check = aes256.encrypt("F-" + (random.nextInt(100) + 1));
            time = aes256.encrypt(""+ LocalDateTime.now());
        }else {
            id = aes256.encrypt("id"+ (random.nextInt(1000)+1));
            name = aes256.encrypt("name"+(random.nextInt(1000)+1));
            r_username = aes256.encrypt("username"+(random.nextInt(1000)+1));
            email = aes256.encrypt("email"+(random.nextInt(1000)+1));
            check = aes256.encrypt("S-" + (random.nextInt(100) + 1));
            time = aes256.encrypt(""+ LocalDateTime.now());
        }
        send = new LoginResultDO(id, name, r_username, email, check, time);
        return send;
    }




}
