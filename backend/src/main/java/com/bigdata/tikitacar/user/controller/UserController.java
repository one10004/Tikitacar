package com.bigdata.tikitacar.user.controller;

import com.bigdata.tikitacar.user.dto.request.UserRegisterRequestDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @ApiOperation("회원가입")
    @PostMapping("")
    public Object signup(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
        return null;
    }

    @ApiOperation("회원 정보 불러오기")
    @GetMapping("")
    public Object getUserInfo(@RequestHeader(value="Authorization") String token){
        return null;
    }

    @ApiOperation("회원 정보 업데이트(수정)")
    @PutMapping("/{id}")
    public Object updateUserInfo(@PathVariable("id") Long id){
        return null;
    }

    @ApiOperation("회원 탈퇴")
    @DeleteMapping("/{id}")
    public Object deleteUser(@PathVariable("id") Long id){
        return null;
    }

    @ApiOperation("이메일 중복체크")
    @GetMapping("/email/{email}")
    public Object checkEmail(@PathVariable("email") String email){
        return null;
    }

    @ApiOperation("닉네임 중복체크")
    @GetMapping("/nickname/{nickname}")
    public Object checkNickname(@PathVariable("nickname") String nickname){
        return null;
    }
}
