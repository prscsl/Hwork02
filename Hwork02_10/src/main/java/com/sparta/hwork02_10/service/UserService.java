package com.sparta.hwork02_10.service;

import com.sparta.hwork02_10.dto.LoginRequestDto;
import com.sparta.hwork02_10.dto.SignupRequestDto;
import com.sparta.hwork02_10.dto.TokenDto;
import com.sparta.hwork02_10.model.RefreshToken;
import com.sparta.hwork02_10.model.User;
import com.sparta.hwork02_10.repository.RefreshTokenRepositroy;
import com.sparta.hwork02_10.repository.UserRepository;
import com.sparta.hwork02_10.security.provider.JwtAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RefreshTokenRepositroy refreshTokenRepositroy;
    private final JwtAuthProvider jwtAuthProvider;

    public String registerUser(SignupRequestDto signupRequestDto) throws IllegalAccessException {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
        String samepassword = signupRequestDto.getSamepassword();

        // 회원 Id 중복 확인
        Optional<User> overlap = userRepository.findByUsername(username);
        if(overlap.isPresent()) {
            return "중복된 닉네임입니다.";
        }
        if(!password.equals(samepassword)) {
            return "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
        }

        // 패스워드 암호화
        password = passwordEncoder.encode(signupRequestDto.getPassword());
        LoginRequestDto dto = LoginRequestDto.builder()
                .username(username)
                .password(password)
                .build();
        User user = new User(dto);
        userRepository.save(user);
        return user.getUsername()+"님 환영합니다";
    }

    public String login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        Optional <User> id = userRepository.findByUsername(loginRequestDto.getUsername());
//        if(!id.get().getPassword().equals(loginRequestDto.getPassword())){
//            return "사용자를 찾을 수가 없습니다.";
//        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtAuthProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepositroy.save(refreshToken);

        response.addHeader("Access-Token", tokenDto.getGrantType()+" "+tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", tokenDto.getRefreshToken());

        return authentication.getName()+"님 로그인 되셨습니다.";
    }
}
