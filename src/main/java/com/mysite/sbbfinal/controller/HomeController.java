package com.mysite.sbbfinal.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbbfinal.domain.User;
import com.mysite.sbbfinal.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
	
	@GetMapping({"/", "/home"})
	public String home() {
		return "home";
	}
	
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    // 회원가입 폼
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }
    
    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username,
                         @RequestParam("password") String password) {

        // 사용자 생성
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // 비밀번호 인코드
        user.setEnabled(true);

        // 사용자 저장
        userMapper.save(user);

        // ROLE_USER(기본 권한) 추가 (roles 테이블에서 ROLE_USER의 id가 1이라고 가정)
        userMapper.insertUserRole(user.getId(), 1L);

        return "redirect:/login";
    }
}
