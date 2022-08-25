package com.sparta.hwork02_10.security.filter;

import com.sparta.hwork02_10.security.provider.JwtAuthProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthFilter extends GenericFilter {

    private final JwtAuthProvider jwtAuthProvider;

    public JwtAuthFilter(JwtAuthProvider jwtAuthProvider)  {
        this.jwtAuthProvider = jwtAuthProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. Request Header 에서 토큰을 꺼냄
        String jwt = jwtAuthProvider.resolveToken((HttpServletRequest) request);
        if (jwt != null && jwtAuthProvider.validateToken(jwt)) {   // token 검증
            Authentication auth = jwtAuthProvider.getAuthentication(jwt);    // 인증 객체 생성
            SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContextHolder에 인증 객체 저장
        }
        chain.doFilter(request, response);
    }
}
