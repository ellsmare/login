package com.example.login.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "(?=.*[a-z0-9]).{4,10}", message = "아이디는 4~10자 영문 소문자, 숫자를 사용하세요.")
    private String username;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[#?!@$ %^&*a-zA-Z0-9]).{8,15}", message = "비밀번호는 8~15자 영문 대문자, 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
    @NotBlank(message="이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    //관리자 todo
    private boolean admin = false;
    private String adminToken = "";

    /* DTO -> Entity */
//    public UserEntity toEntity() {
//         UserEntity userEntity = UserEntity.builder()
//                .username(username)
//                .password(password)
//                .email(email)
//                .role(UserRole.USER)  //todo
//                .nickname(username)
//                .build();
//        return userEntity;
//    }

}
