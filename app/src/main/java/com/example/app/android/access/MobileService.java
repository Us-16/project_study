package com.example.app.android.access;

import com.example.app.user.teacher.Teacher;
import com.example.app.user.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MobileService {
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    public ArrayList<String> LoginTeacher(String username, String password){
        Optional<Teacher> result = teacherRepository.findByUsername(username);
        if(result.isEmpty()){
            return null;
        }
        ArrayList<String> resultList = new ArrayList<>();
        System.out.println(result.get().getUsername());
        System.out.println(result.get().getPassword());
        System.out.println(passwordEncoder.matches(password, result.get().getPassword()));//비교할 넘, 암호화된 넘 -> true/false

        return resultList;
    }
}
