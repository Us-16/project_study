package com.example.app.upload;

import com.example.app.AppApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.http.Path;

@Controller
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @GetMapping("/test/upload")
    public String uploadWriteForm(){
        return "content/upload/uploadTest";
    }

    /**
     * 파일을 받는 곳. 정리 잘 해놓을 것
     * @param upload DTO일 뿐임. 사실상 포장지랑 같은 거
     * @param model HTML에 올려놓기 위해서 설정한 거
     * @param file 얘가 진짜 파일임
     * @return
     * @throws Exception
     */
    @PostMapping("/test/upload")
    public String uploadWritePro(Upload upload, Model model, MultipartFile file) throws Exception{
        System.out.println("File: " + file);
        System.out.println("Upload: " + upload);

        uploadService.write(upload, file);

        model.addAttribute("message", "글작성 완료");
        model.addAttribute("searchUrl", "/question/list");
        return "redirect:/";
    }

    @GetMapping("/test/view/{id}")
    public String uploadView(Model model, @PathVariable("id") Long id){

        model.addAttribute("testboard", uploadService.getView(id));

        return "content/upload/uploadView";
    }
}
