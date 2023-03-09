package com.example.app.android.access;

import com.example.app.user.teacher.Teacher;
import com.example.app.user.teacher.TeacherRepository;
import com.example.app.util.AES256;
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
    private final PasswordEncoder passwordEncoder;
    private final AES256 aes256 = new AES256();

    public ArrayList<String> LoginTeacher(String username, String password) throws Exception {
        //일단은 학생 로그인처럼 똑같이 해놓겠음
        //다만, 이 방식은 절대로 안전한 방식이 아님을 인지하고 있으셈. 고민하고 해결해야 뭐 만들어봣다는 소리 할 수 있는 거임
        Optional<Teacher> result = teacherRepository.findByUsername(username);
        ArrayList<String> resultList = new ArrayList<>();
        Random random = new Random();

        if(result.equals(Optional.empty()) || (!passwordEncoder.matches(password, result.get().getPassword()))){
            resultList.add(aes256.encrypt("id"+ (random.nextInt(1000)+1)));
            resultList.add(aes256.encrypt("name"+(random.nextInt(1000)+1)));
            resultList.add(aes256.encrypt("username"+(random.nextInt(1000)+1)));
            resultList.add(aes256.encrypt("email"+(random.nextInt(1000)+1)));
            resultList.add(aes256.encrypt("F-" + (random.nextInt(100) + 1)));
            resultList.add(aes256.encrypt(""+ LocalDateTime.now()));
            return resultList;
        }else {
            resultList.add(aes256.encrypt(result.get().getId().toString()));
            resultList.add(aes256.encrypt(result.get().getName()));
            resultList.add(aes256.encrypt(username));
            resultList.add(aes256.encrypt(result.get().getEmail()));
            resultList.add(aes256.encrypt("S-" + (random.nextInt(2000) + 1) + "00"));
            resultList.add(aes256.encrypt("" + LocalDateTime.now()));
        }
        return resultList;
    }
}
