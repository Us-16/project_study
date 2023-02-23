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
    /**
     * 아이디 중복 확인
     * @param username
     * @return
     */
    public String CheckDup(String username){
        if(this.studentRepository.findByUsername(username).equals(Optional.empty())) {
            return "true";
        }
        return "false";
    }
    /**
     * 학생로그인
     * @param username
     * @param password
     * @return ArrayList<String> (id(고유번호), name(성명), username, password, check(권한), time(접속시간))
     * @throws Exception
     */
    public ArrayList<String> LoginStudent(String username, String password) throws Exception{
        Optional<Student> result = studentRepository.findByUsername(username);
        ArrayList<String> resultList = new ArrayList<String>();
        Random random = new Random();

        //아이디 조회 안되거나 비밀번호 틀렸을 때
        if(result.equals(Optional.empty()) || (!result.get().getPassword().equals(password))){
            String[] fail_input = {aes256.encrypt("id"+ (random.nextInt(1000)+1)), aes256.encrypt("name"+(random.nextInt(1000)+1)), aes256.encrypt("username"+(random.nextInt(1000)+1)),
                    aes256.encrypt("email"+(random.nextInt(1000)+1)), aes256.encrypt("F-" + (random.nextInt(100) + 1)), aes256.encrypt(""+LocalDateTime.now())};
            resultList.addAll(Arrays.asList(fail_input));
            return resultList;
        }

        // 제대로 적은 게 맞다면!
        String[] input = {aes256.encrypt(result.get().getId().toString()), aes256.encrypt(result.get().getName()), aes256.encrypt(username),
                aes256.encrypt(result.get().getEmail()), aes256.encrypt("S-"+(random.nextInt(2000) +1) + "00"), aes256.encrypt(""+LocalDateTime.now())};

        resultList.addAll(Arrays.asList(input));

        return resultList;
    }
}
