package com.sparta.hwork02_10.controller;

import com.sparta.hwork02_10.dto.MemoRequestDto;
import com.sparta.hwork02_10.dto.MemoResponseDto;
import com.sparta.hwork02_10.model.Memo;
import com.sparta.hwork02_10.security.UserDetailsImpl;
import com.sparta.hwork02_10.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/api/auth/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetail) {

        return memoService.createMemo(requestDto, userDetail.getUsername());
    }

    @GetMapping("/api/memos")
    public List<MemoResponseDto> getMemos() {
        return memoService.getMemo();
    }

    @GetMapping("/api/memos/{id}")
    public Memo getDetailMemo(@PathVariable Long id){
        return memoService.getDetailMemo(id);
    }


    @DeleteMapping("/api/auth/memos/{id}")
    public String deleteMemo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetail) {
        return memoService.delete(id, userDetail.getUsername());

    }


    @PutMapping("/api/auth/memos/{id}")
    public String updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetail) {
        return memoService.update(id, requestDto, userDetail.getUsername());
    }



}