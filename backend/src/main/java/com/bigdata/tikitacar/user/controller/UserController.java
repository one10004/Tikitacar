package com.bigdata.tikitacar.user.controller;

import com.bigdata.tikitacar.user.dto.request.UserDeleteCheckRequsetDto;
import com.bigdata.tikitacar.user.dto.request.UserModifyRequestDto;
import com.bigdata.tikitacar.user.dto.request.UserRegisterRequestDto;
import com.bigdata.tikitacar.user.dto.response.UserFindResponseDto;
import com.bigdata.tikitacar.user.service.UserService;
import com.bigdata.tikitacar.util.Base64Service;
import com.bigdata.tikitacar.util.EmailService;
import com.bigdata.tikitacar.util.JwtService;
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

    @Autowired
    private JwtService jwtService;

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
        String link = "http://j3a106.p.ssafy.io:8081/auth/" + encoding;

        emailService.sendSimpleMessage(email, "Tikitacar 가입 인증메일 입니다.",
                email + "님 가입을 환영합니다.<br>" + "가입을 인증하기 위해서 아래의 링크를 클릭해주십시오. <br> "
                + "<strong>아래 링크를 클릭하면 인증이 완료됩니다.</strong><br> " + "<a href='" + link + "'>링크</a>");

        return response;
    }

    @ApiOperation("회원 정보 불러오기")
    @GetMapping("")
    public Object getUserInfo(@RequestHeader(value="Authorization") String token){
        Map<String, Object> map = new HashMap<String, Object>();
        ResponseEntity response = null;
        String email = jwtService.getEmailFromToken(token);
        UserFindResponseDto userFindResponseDto = userService.findUserByEmail(email);

        if(userFindResponseDto != null){
            map.put("msg", "회원 정보 불러오기 성공");
            map.put("status", "success");
            map.put("user", userFindResponseDto);
            response = new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("msg", "회원 정보 불러오기 실패");
            map.put("status", "fail");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @ApiOperation("회원 정보 업데이트(수정)")
    @PutMapping("")
    public Object updateUserInfo(@RequestBody UserModifyRequestDto userModifyRequestDto, @RequestHeader("Authorization") String token){
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<String, Object>();

        String email = jwtService.getEmailFromToken(token);
        userService.modifyUserInfo(userModifyRequestDto, email);

        map.put("msg", "회원 정보 수정 성공");
        map.put("status", "success");
        response = new ResponseEntity(map, HttpStatus.OK);
        return response;
    }

    @ApiOperation("이메일 비밀번호 체크")
    @PostMapping("/check")
    public Object checkWhenDelete(@RequestBody UserDeleteCheckRequsetDto userDeleteCheckRequsetDto, @RequestHeader("Authorization") String token){
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        String email = jwtService.getEmailFromToken(token);
        String password = userService.findPasswordByEmail(email);

        if(password.equals(userDeleteCheckRequsetDto.getPassword())){
            map.put("msg", "이메일, 비밀번호 일치");
            map.put("status", "success");
            response = new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("msg", "이메일, 비밀번호 불일치");
            map.put("status", "fail");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @ApiOperation("회원 탈퇴")
    @DeleteMapping("")
    public Object deleteUser(@RequestHeader(value="Authorization") String token){
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        String email = jwtService.getEmailFromToken(token);
        userService.deleteUser(email);
        map.put("msg", "회원탈퇴 성공");
        map.put("status", "success");
        response = new ResponseEntity(map, HttpStatus.OK);

        return response;
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

        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        int count = userService.findNicknameDuplicate(nickname);

        if(count == 0){
            map.put("msg", "성공");
            map.put("status", "success");
            response = new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("msg", "닉네임 중복");
            map.put("status", "fail");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
