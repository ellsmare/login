package com.example.login.user;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String username);

    Optional<UserEntity> findUsernameByEmail(String email);

    //Page<UserEntity> findAll(pageable);


    boolean existsByEmail(String email);

    //Optional<UserEntity> findById(long idx);
   // List<UserEntity> findAll(UserEntity userEntity);
}
