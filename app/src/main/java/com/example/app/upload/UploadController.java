package com.example.app.upload;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@PropertySource("classpath:/common.properties")
public class UploadController {
    @Value("${file.path}")
    private String savePath;

    @PostMapping("/test")
    String Register(HttpSession session, Model model, @RequestParam(name="file", required = false)MultipartFile file,
                    @Valid RegisterForm registerForm, BindingResult bindingResult) throws IOException{
        if(!file.isEmpty()){
            String uuid = UUID.randomUUID().toString()+".jpg";
            File converFile = new File(savePath, uuid);
            file.transferTo(converFile);

        }
    }
}
