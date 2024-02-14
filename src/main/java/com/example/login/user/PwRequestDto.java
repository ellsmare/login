package com.example.login.user;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PwRequestDto {
    private long idx;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "(?=.*[#?!@$ %^&*a-zA-Z0-9]).{8,15}", message = "비밀번호는 8~15자 영문 대문자, 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message="이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    public UserEntity toEntity(){
        UserEntity pw = new UserEntity();
        pw.setPassword(password);
        return pw;
    }

}