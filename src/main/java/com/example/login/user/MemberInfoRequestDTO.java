package com.example.login.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.plaf.PanelUI;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoRequestDTO {

    private String nickname;
    private String password;
    private String infoText;   //코멘트창

    @Builder
    public  MemberInfoRequestDTO(UserEntity userEntity){
        this.nickname= userEntity.getNickname();
        this.password= userEntity.getPassword();
        this.infoText= userEntity.getInfoText();
    }
}
