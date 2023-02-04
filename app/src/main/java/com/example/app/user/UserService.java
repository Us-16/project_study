package com.example.app.user;

import com.example.app.DataNotFoundException;
import com.example.app.aes.AES256;
import com.example.app.android.BoardApiController;
import com.example.app.android.dto.LoginTestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private AES256 aes256 = new AES256();


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

    public Student createStudent(String name, String username, String password, String P_id1, String P_id2, String email, String school, String grade){
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
    public String CheckDup(String username){
        log.info("UserService: " + username);
        log.info("Duplicate Test" + this.studentRepository.findByUsername(username));
        System.out.println("Duplicate Test" + this.studentRepository.findByUsername(username));

        if(this.studentRepository.findByUsername(username).equals(Optional.empty())) {
            System.out.println("없다는데?");
            return "true";
        }
        System.out.println("있다는데?");
        return "false";
    }

    public ArrayList<String> LoginTest(String username, String password) throws Exception{
        System.out.println("LoginTest: "+username+ " " + password);
        Optional<Student> result = studentRepository.findByUsername(username);
        ArrayList<String> resultList = new ArrayList<String>();
        Random random = new Random();


        String check = this.aes256.encrypt("false");
        System.out.println("username: " + username);


        if(result.equals(Optional.empty()) || (!result.get().getPassword().equals(password))){ //없는 아이디 쳤을 때
            resultList.add(0, aes256.encrypt(""+ (random.nextInt(1000)+1) + "f"));
            resultList.add(1, aes256.encrypt(""+(random.nextInt(1000)+1)));
            resultList.add(2, aes256.encrypt(""+(random.nextInt(1000)+1)));
            resultList.add(3, aes256.encrypt(""+(random.nextInt(1000)+1)));
            resultList.add(4, aes256.encrypt("" + (random.nextInt(100) + 1)));
            return resultList;
        }

        // 제대로 적은 게 맞다!
        check = aes256.encrypt(""+(random.nextInt(2000) +1) + "00");
        username = aes256.encrypt(username);
        String id = aes256.encrypt(result.get().getId().toString());
        String name = aes256.encrypt(result.get().getName());
        String email = aes256.encrypt(result.get().getEmail());

        resultList.add(0, id);
        resultList.add(1, name);
        resultList.add(2, username);
        resultList.add(3, email);
        resultList.add(4, check);

        return resultList;

    }
}
