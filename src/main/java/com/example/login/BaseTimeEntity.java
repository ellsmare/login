package com.example.login;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 자식클래스 포함
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}

//@EntityListeners(AuditingEntityListener.class)
//@EnableJpaAuditing 사용

// @CreationTimestamp & @UpdateTimestamp  숫자형 TIME_ZONE TIME_ZONE에 따라 입력값이 다르게 나타날 수 있다.
// @CreatedDate & @LastModifiedDate  문자형