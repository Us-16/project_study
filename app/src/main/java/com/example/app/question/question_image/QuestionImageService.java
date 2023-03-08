package com.example.app.question.question_image;

import com.example.app.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionImageService {
    private final QuestionImageRepository questionImageRepository;

    public void create(Question question, String filePath, String fileName, long filesize){
        QuestionImage questionImage = new QuestionImage();
        questionImage.setQuestion(question);
        questionImage.setFilename(fileName);
        questionImage.setFilepath(filePath);
        questionImage.setFilesize(filesize);
        this.questionImageRepository.save(questionImage);
    }

    public void write(Question question, List<MultipartFile> files) throws Exception{
        for(MultipartFile file : files) {
            String projectPath = System.getProperty("user.dir") + "/app/src/main/resources/static/files";
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_"+file.getOriginalFilename();
            File saveFile = new File(projectPath, fileName);
            file.transferTo(saveFile);
            this.create(question, "/files/"+fileName, fileName, file.getSize());
        }
    }
}
