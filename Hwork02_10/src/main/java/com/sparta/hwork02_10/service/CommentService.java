package com.sparta.hwork02_10.service;

import com.sparta.hwork02_10.dto.CommentDto;
import com.sparta.hwork02_10.dto.MemoRequestDto;
import com.sparta.hwork02_10.model.Comment;
import com.sparta.hwork02_10.model.Memo;
import com.sparta.hwork02_10.model.User;
import com.sparta.hwork02_10.repository.CommentRepository;
import com.sparta.hwork02_10.repository.UserRepository;
import com.sparta.hwork02_10.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final MemoRepository memoRepository;

    public Comment createComment(Long id, CommentDto commentDto, String username ) {
        // 댓글 작성자 정보
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("회원가입이 필요합니다")
        );
        //메모 타이틀
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("회원가입이 필요합니다")
        );

        Comment comment = new Comment(memo, user, commentDto);

        commentRepository.save(comment);

        return comment;
    }


    @Transactional
    public String updateComment(Long id, CommentDto commentDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("회원가입이 필요합니다")
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다")
        );
        if(!user.getUsername().equals(comment.getUsername()))
            return "작성자만 수정할 수 있습니다.";
        comment.commentUpdate(commentDto);
        return "댓글이 수정되었습니다";
    }



    public String deleteComment(Long id, String username){
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("회원가입이 필요합니다")
        );
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다")
        );
        if(!user.getUsername().equals(comment.getUsername()))
            return "작성자만 삭제할 수 있습니다.";
        commentRepository.deleteById(id);
        return "댓글이 삭제되었습니다.";
    }
}
