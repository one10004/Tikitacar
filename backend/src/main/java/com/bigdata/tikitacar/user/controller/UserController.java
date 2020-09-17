package com.bigdata.tikitacar.user.controller;

import com.bigdata.tikitacar.user.dto.request.UserRegisterRequestDto;
import com.bigdata.tikitacar.user.service.UserService;
import com.bigdata.tikitacar.util.Base64Service;
import com.bigdata.tikitacar.util.EmailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Base64Service base64Service;

    @ApiOperation("회원가입")
    @PostMapping("")
    public Object signup(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        userService.registerUser(userRegisterRequestDto);
        map.put("msg", "회원가입 성공");
        map.put("status", "success");
        response = new ResponseEntity(map, HttpStatus.OK);

        String email = userRegisterRequestDto.getEmail();
        String encoding = base64Service.encode(email);
        String link = "http://j3a106.p.ssafy.io/user/auth/" + encoding;

        emailService.sendSimpleMessage(email, "Tikitacar 가입 인증메일 입니다.",
                email + "님 가입을 환영합니다.<br>" + "가입을 인증하기 위해서 아래의 링크를 클릭해주십시오. <br> "
                + "<strong>아래 링크를 클릭하면 인증이 완료됩니다.</strong><br> " + "<a href='" + link + "'>링크</a>");

        return response;
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
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        int count = userService.findEmailDuplicate(email);

        if(count == 0){
            map.put("msg", "성공");
            map.put("status", "success");
            response = new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("msg", "이메일 중복");
            map.put("status", "fail");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @ApiOperation("닉네임 중복체크")
    @GetMapping("/nickname/{nickname}")
    public Object checkNickname(@PathVariable("nickname") String nickname){
        return null;
    }
}
