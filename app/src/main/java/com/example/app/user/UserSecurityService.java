package com.example.app.user;

import com.example.app.user.teacher.Teacher;
import com.example.app.user.teacher.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService {
    private final TeacherRepository teacherRepository;
    //private final StudentRepository studentRepository; //아마 이게 여기서 쓰일 일은 없을 거 같다만...

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Teacher> _Teacher = this.teacherRepository.findByUsername(username);
        if(_Teacher.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        Teacher teacher = _Teacher.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("wkdgyfla97".equals(username)){
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        return new User(teacher.getUsername(), teacher.getPassword(), authorities);
    }
}
