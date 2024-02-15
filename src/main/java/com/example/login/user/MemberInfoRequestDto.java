package com.example.login.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoRequestDto {

    private String nickname;
    private String password;
    private String infoText;   //코멘트창

    @Builder
    public MemberInfoRequestDto(UserEntity userEntity){
        this.nickname= userEntity.getNickname();
        this.password= userEntity.getPassword();
        this.infoText= userEntity.getInfoText();
    }
}
