package com.example.login.user;


public enum UserRole {
    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}

//String의 role에서 오타가 발생할 수 있다--> enum
//자바의 enum타입으 엔티티속성으로 받을 수 있음
//enum을 사용시 기본적으로 순서가 저장됨으로 enum의 순서가 바뀔시 문제가됨.
//enumtype.string 옵션 권장!!!!!