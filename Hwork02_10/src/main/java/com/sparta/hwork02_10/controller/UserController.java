package com.sparta.hwork02_10.controller;

import com.sparta.hwork02_10.dto.LoginRequestDto;
import com.sparta.hwork02_10.dto.SignupRequestDto;
import com.sparta.hwork02_10.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("api/user/signup")
    public String signup(@RequestBody @Valid SignupRequestDto signupRequestDto) throws IllegalAccessException {
        return userService.registerUser(signupRequestDto);
    }

    // 로그인
    @PostMapping("api/user/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }

}
