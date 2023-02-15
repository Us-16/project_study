package com.example.app;

import com.example.app.android.BoardApiController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        return "content/index";
    }
}
