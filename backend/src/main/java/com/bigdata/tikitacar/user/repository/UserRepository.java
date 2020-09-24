package com.bigdata.tikitacar.user.repository;

import com.bigdata.tikitacar.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // 이메일 중복 체크
    int countByEmail(String email);
    
    // 닉네임 중복 체크
    int countByNickname(String nickname);

    // 이메일로 User 찾기
    Optional<User> findByEmail(String email);

    // id로 User 찾기
    Optional<User> findById(Long id);

    // 로그인
    Optional<User> findByEmailAndPassword(String email, String password);


}
