package com.example.app.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    //private HttpServletRequest request;

    private final String signup = "content/login/signup_form";
    private final String login = "content/login/login_form";
    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm){
        return signup;
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return signup;
        }
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return signup;
        }
        try{
            userService.create(userCreateForm.getUsername(), userCreateForm.getPassword1(), userCreateForm.getName(), userCreateForm.getBirthday(), userCreateForm.getEmail(), userCreateForm.getSchool());
        }catch(DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return signup;
        }catch(Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return signup;
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return login;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model){
        String uri = request.getHeader("Referer");
        if(uri != null && !uri.contains("/login"))
            request.getSession().setAttribute("prevPage", uri);
        return login;
    }
}
