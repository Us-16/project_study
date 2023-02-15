package com.example.app.upload;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {
    private final UploadRepository uploadRepository;
    public void write(Upload upload, MultipartFile file) throws Exception{
        String projectPath = System.getProperty("user.dir") +
                "/app/src/main/resources/static/files";
        System.out.println(projectPath);
        UUID uuid = UUID.randomUUID(); // 랜덤으로 이름을 만들어줌
        String fileName = uuid+"_"+file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

        upload.setFilename(fileName);
        upload.setFilepath("/files/" + fileName);

        System.out.println("Service Upload: " + upload);

        uploadRepository.save(upload);
    }

    public List<Upload> getList(){
        return uploadRepository.findAll();
    }

    public Upload getView(Long id){
        return uploadRepository.findById(id).get();
    }

    public void uploadDelete(Long id){
        uploadRepository.deleteById(id);
    }
}
