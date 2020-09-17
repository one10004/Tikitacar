package com.bigdata.tikitacar.user.repository;

import com.bigdata.tikitacar.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // 이메일 중복 체크
    int countByEmail(String email);
    
    // 닉네임 중복 체크
    int countByNickname(String nickname);
}
