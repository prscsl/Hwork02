package com.sparta.hwork02_10.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class MemoRequestDto {
    private final String title;
    private final String contents;
}
