package com.bigdata.tikitacar.user.service;

import com.bigdata.tikitacar.user.dto.request.UserLoginRequestDto;
import com.bigdata.tikitacar.user.dto.request.UserRegisterRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
@Rollback(false)
class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void register(){
        UserRegisterRequestDto user = UserRegisterRequestDto.builder()
                .email("honol82@naver.com")
                .password("test1234")
                .nickname("honol")
                .birth(LocalDate.of(1994,2,6))
//                .birth("1994-02-06")
                .gender("남")
                .phone("010-0000-0000")
                .address("서울시")
                .addressDetail("강서구")
                .build();
        userService.registerUser(user);
    }
}