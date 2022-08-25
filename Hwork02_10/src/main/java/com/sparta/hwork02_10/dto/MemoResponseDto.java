package com.sparta.hwork02_10.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class MemoResponseDto {
    private String username;
    private String title;
    private String contents;
    private LocalDateTime creatT;
}
