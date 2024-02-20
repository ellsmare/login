package com.example.login.user;

import com.example.login.auth.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    public final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";


    //내 정보 수정





    /* getInfo */
    public UserEntity getInfo(long id) {

        //value="${principal.userentity?.username}"
        UserEntity userEntity = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        //model.addAttribute("userEntity",userEntity.); , Model model
        return userEntity;
    }

    /* findUsername  */
    public String findUsernameByEmail (String email){
        System.out.println(email);
        boolean ok = memberRepository.existsByEmail(email);
        System.out.println(ok);
        if (ok == false) {
            System.out.println("existsByEmail 실패했습니다:: existsByEmail null");
            throw new IllegalArgumentException("등록된 사용자가 없습니다.");
        }

        UserEntity idOrPw = memberRepository.findUsernameByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        String username = idOrPw.getUsername();
        System.out.println("____MemberService____findByUsername(email) : " + username);
        //model.addAttribute("username",username); , Model model
        return username;
    }

    //*로그인- (jwt) -> security  sesssion--> jwt*/
    /**
     * 로그인
     * @param //username - 로그인 ID
     * @param //password - 비밀번호
     * @return 회원 상세정보
     */
    // @Transactional(readOnly = true)
    public UserEntity signIn (LoginRequestDto loginRequestDto, HttpServletResponse res){
        String loginId = loginRequestDto.getUsername();
        String loginPw = loginRequestDto.getPassword();

        // 사용자 확인
        // Optional<UserEntity> member = memberRepository.findByUsername(username);
        // if(member==null) throw new Exception("멤버 조회 실패");
        UserEntity principal = memberRepository.findByUsername(loginId).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        System.out.println("사용자가:: " + loginId);
        String userPW = principal.getPassword();

        // 비밀번호 확인
        boolean ok = passwordEncoder.matches(loginPw, userPW);
        if (!ok) { //입력pw, 엔티티pw
            System.out.println("signin 실패했습니다:: loginPw null");
            throw new IllegalArgumentException("등록된 사용자가 없습니다.");
        }
        System.out.println("비밀번호가:: " + loginPw);
        System.out.println("비밀번호가:: " + userPW);

        // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
        String token = jwtUtil.createToken(principal.getUsername(), principal.getRole());
        jwtUtil.addJwtToCookie(token, res);

        System.out.println("서비스 통과 : " + principal);
        return principal;
    }

    /*회원가입 */   // 문제 :: 관리자 전환 안됨 id="btn-login  함수
     @Transactional
    public void register (@Valid RegisterRequestDto requestDto){
        System.out.println("register::" + requestDto);

        String password = passwordEncoder.encode(requestDto.getPassword());
        String username = requestDto.getUsername();
        String email = requestDto.getEmail();
        UserRole role = UserRole.USER;

        // 회원 중복 확인
        Optional<UserEntity> checkUsername = memberRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        Optional<UserEntity> checkEmail = memberRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        if (requestDto.isAdmin()) {  //여기서 모르는듯? dto entity 확인하기
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRole.ADMIN;   //todo 실패했다. 수정하기
            System.out.println(role);
        }

        UserEntity userEntity = new UserEntity(username, username, password, email, role);
        System.out.println(role);

        try {
            System.out.println("save resiger try::" + username);
            System.out.println("save resiger try::" + password);
            System.out.println("save resiger try::" + email);
            memberRepository.save(userEntity);

        } catch (Exception e) {
            System.out.println("service -signupFailed : " + e.getMessage());
        }
    }
}
