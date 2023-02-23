package com.example.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import retrofit2.http.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final LoginSuccessHandler loginSuccessHandler;

    /**
     * BCrypy -> SHA-2 이상의 알고리즘, 8bit로 결합된 Hash
     * 랜덤하게 생성된 솔트 지원
     * @return Bcrypt로 변환한 값
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests().requestMatchers(
                        new AntPathRequestMatcher("/**")).permitAll()
                .and()
                .csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/api/**"))
                .and()
                .csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/test/**"))
                .and()
                .headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                .formLogin().loginPage("/user/login").successHandler(loginSuccessHandler)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).logoutSuccessUrl("/").invalidateHttpSession(true);
        return http.build();
    }
}
