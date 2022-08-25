package com.sparta.hwork02_10.service;

import com.sparta.hwork02_10.dto.MemoDetailDto;
import com.sparta.hwork02_10.dto.MemoRequestDto;
import com.sparta.hwork02_10.dto.MemoResponseDto;
import com.sparta.hwork02_10.model.User;
import com.sparta.hwork02_10.model.Memo;
import com.sparta.hwork02_10.repository.CommentRepository;
import com.sparta.hwork02_10.repository.UserRepository;
import com.sparta.hwork02_10.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    public Memo createMemo(MemoRequestDto requestDto, String username ) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("회원가입이 필요합니다.")
        );
        Memo memo = new Memo(requestDto, user);

        memoRepository.save(memo);

        return memo;
    }

    public String delete(Long id, String username) throws IllegalArgumentException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다")
        );
        if(!memo.getUsername().equals(user.getUsername()))
            return "작성자만 삭제할 수 있습니다.";
        memoRepository.deleteById(id);
         return "메모가 삭제되었습니다.";
    }

    @Transactional
    public String update(Long id, MemoRequestDto requestDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다")
        );
        if(!memo.getUsername().equals(user.getUsername()))
            return "작성자만 수정할 수 있습니다.";
        memo.update(requestDto);
        return "메모가 수정되었습니다";
    }

    public List<MemoResponseDto> getMemo() {
        List<Memo> memos = memoRepository.findAllByOrderByModifiedAtDesc();
        List<MemoResponseDto> rMemo = new ArrayList<>();
        for(Memo memo : memos ){
            MemoResponseDto rMemos = MemoResponseDto.builder()
                    .title(memo.getTitle())
                    .username(memo.getUsername())
                    .creatT(memo.getCreateAt())
                    .build();
            rMemo.add(rMemos);
        }
        return rMemo;
    }

    public Memo getDetailMemo(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 메모가 없습니다.")
        );
        return memo;
    }
}