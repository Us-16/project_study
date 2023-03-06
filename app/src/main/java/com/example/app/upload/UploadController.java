package com.example.app.upload;

import com.example.app.AppApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import retrofit2.http.Path;

import java.util.Arrays;
import java.util.List;

@Controller
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @GetMapping("/test/upload")
    public String uploadWriteForm(){
        return "content/upload/uploadTest";
    }

    /**
     * 파일 받는 곳
     * @param upload
     * @param model
     * @param mtfRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/test/upload")
    public String uploadWritePro(Upload upload, Model model, MultipartHttpServletRequest mtfRequest) throws Exception{
        List<MultipartFile> files = mtfRequest.getFiles("file");
        //System.out.println("File: " + file);
        //System.out.println("Upload: " + upload);
        String result = "";
        for(MultipartFile file : files){
            result += file.getName() + " ::: ";
            result += file.getOriginalFilename() + " ::: ";
            result += file.getContentType() + " ::: ";
            //result += Arrays.toString(file.getBytes()) + " ::: ";
            result += file.getInputStream() + " ::: ";
            result += file.getResource() + " ::: ";
            result += file.getSize() + " ::: ";
            result += "\n\n";
        }
        System.out.println("result is \n" + result);

        //uploadService.write(upload, file);

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
