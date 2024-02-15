package com.example.login.post;

import com.example.login.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequestDto {

    private int replyId;
    private String content;
    private PostEntity postEntity;
    private UserEntity userEntity;

//    @CreationTimestamp
//    private Timestamp createdAt;
//    @UpdateTimestamp
//    private Timestamp modifiedAt;

    /* Dto -> Entity */

}
