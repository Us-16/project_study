package com.example.app.question;

import com.example.app.DataNotFoundException;
import com.example.app.user.teacher.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList(){
        return this.questionRepository.findAll();
    }

    /**
     * PageReuqest.of(page, 표시할 페이지 수)
     * @param page 조회할 페이지의 번호
     * @return
     */
    public Page<Question> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 40, Sort.by(sorts)); //나중에 여기 건드려서 몇 개씩 볼지 선택하도록 만들어보세요
        return this.questionRepository.findAll(pageable);
    }

    public Question getQuestion(Long id){
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()){
            return question.get();
        }else{
            throw new DataNotFoundException("question not found");
        }
    }

    public String getFilePath(Long q_id){
        return this.questionRepository.getFilePath(q_id);
    }

    public Question create(String subject, String  content, String filePath){
        //Tester
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now()); //test
        this.questionRepository.save(q);
        return q;
    }

    /**
     *
     * @param subject 제목
     * @param content 글내용
     * @param teacher 출제한 강사
     */
    public Question create(String subject, String content, Teacher teacher){
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setTeacher(teacher);
        this.questionRepository.save(q);
        return q;
    }

    public void modify(Question question, String subject, String content){
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void delete(Question question){
        this.questionRepository.delete(question);
    }


}
