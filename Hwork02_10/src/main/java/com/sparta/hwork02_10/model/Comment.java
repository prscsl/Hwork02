package com.sparta.hwork02_10.model;

import com.sparta.hwork02_10.Timestamped;
import com.sparta.hwork02_10.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Comment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;


    @Column(name = "memo_id")
    private Long memoId;

//    private Memo memo;
//    @Column(nullable = false)
//    private String memoTitle;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    public Comment(Memo memo, User user, CommentDto commentDto) {
        this.memoId = memo.getId();
        this.username = user.getUsername();
        this.comment = commentDto.getComment();
    }

    public void commentUpdate(CommentDto commentDto){
        this.comment = commentDto.getComment();
    }

}
