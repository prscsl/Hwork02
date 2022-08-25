package com.sparta.hwork02_10.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hwork02_10.dto.LoginRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "users")
@NoArgsConstructor
@Getter
public class User {

    @GeneratedValue (strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column (nullable = false, unique = true)
    private String username;

    @Column (nullable = false)
    private String password;


    public User(LoginRequestDto loginRequestDto) {
        this.username = loginRequestDto.getUsername();
        this.password = loginRequestDto.getPassword();
    }
}
