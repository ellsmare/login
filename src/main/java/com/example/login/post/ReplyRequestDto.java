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

    private long id;
    private String content;
    private PostEntity postEntity;
    private UserEntity userEntity;

    /* Dto -> Entity */

}
