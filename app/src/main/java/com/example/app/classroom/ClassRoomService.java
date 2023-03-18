package com.example.app.classroom;

import com.example.app.classroom.student.ClassRoomStudent;
import com.example.app.classroom.student.ClassRoomStudentRepository;
import com.example.app.user.student.Student;
import com.example.app.user.student.StudentRepository;
import com.example.app.user.teacher.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClassRoomService {
    private final ClassRoomRepository classRoomRepository;
    private final ClassRoomStudentRepository classRoomStudentRepository;
    private final StudentRepository studentRepository;

    public ClassRoom createClass(Teacher teacher, String title, String code){
        ClassRoom classroom = new ClassRoom();
        classroom.setTeacher(teacher);
        classroom.setCode(code);
        classroom.setTitle(title);
        classroom.setCreateDate(LocalDateTime.now());

        this.classRoomRepository.save(classroom);

        return classroom;
    }

    public Page<ClassRoom> getClassroom(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 20, Sort.by(sorts));
        return this.classRoomRepository.findAll(pageable);
    }

    public void registStudents(ClassRoom classRoom, String students) {
        if (students == null){
            return ;
        }
        ClassRoomStudent crs;
        String[] studentList = students.split(" ");
        for(String student : studentList){
            System.out.println(student); //이거 생각해보니 이메일로 그 계정의 아이디값을 얻어와야하네?!
            Optional<Student> stu = studentRepository.findByEmail(student);
            if(stu.isPresent()) {
                crs = new ClassRoomStudent();
                crs.setClassRoom(classRoom);
                crs.setStudent(stu.get());
                crs.setCreateDate(LocalDateTime.now());
                this.classRoomStudentRepository.save(crs);
                System.out.println(stu.get().getUsername() + "학생이 정상 등록되었습니다.");
            } //try catch 사용해야할 것으로 보임 -> 이번예제 참고하면 좋을 듯
        }
    }

    public ClassRoom getClassroom(Long id){
        Optional<ClassRoom> classroom = classRoomRepository.findById(id);
        return classroom.orElse(null);
    }
    public List<ClassRoomStudent> getClassroomStudent(Long classId){
        System.out.println(classId);
        List<ClassRoomStudent> students = classRoomStudentRepository.findByClassRoom_Id(classId);
        System.out.println(students.size());

        return students.size() == 0 ? null : students;
    }
}
