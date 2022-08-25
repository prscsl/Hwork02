package com.sparta.hwork02_10.controller;

import com.sparta.hwork02_10.dto.CommentDto;
import com.sparta.hwork02_10.model.Comment;
import com.sparta.hwork02_10.security.UserDetailsImpl;
import com.sparta.hwork02_10.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/api/auth/memos/{id}/comment")
    public Comment createComment(@PathVariable Long id, @RequestBody CommentDto commentDto, @AuthenticationPrincipal UserDetailsImpl userDetail)  {
        return commentService.createComment(id, commentDto, userDetail.getUsername());
    }


    @PutMapping("api/auth/memos/comment/{id}")
    public String updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto, @AuthenticationPrincipal UserDetailsImpl userDetail) {
        return commentService.updateComment(id, commentDto, userDetail.getUsername());
    }

    @DeleteMapping("api/auth/memos/comment/{id}")
    public String deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetail) {
        return commentService.deleteComment(id, userDetail.getUsername());
    }

}
