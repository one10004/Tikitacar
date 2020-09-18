package com.bigdata.tikitacar.user.controller;

import com.bigdata.tikitacar.user.dto.request.UserLoginRequestDto;
import com.bigdata.tikitacar.user.dto.response.UserLoginResponseDto;
import com.bigdata.tikitacar.user.entity.User;
import com.bigdata.tikitacar.user.service.UserService;
import com.bigdata.tikitacar.util.Base64Service;
import com.bigdata.tikitacar.util.JwtService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    Base64Service base64Service;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @ApiOperation("로그인")
    @PostMapping("/login")
    public Object login(@RequestBody UserLoginRequestDto userLoginRequestDto){
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        UserLoginResponseDto userLoginResponseDto = userService.login(userLoginRequestDto);
        String email = userLoginRequestDto.getEmail();

        if(userLoginResponseDto != null){
            if(userLoginResponseDto.getAuth() == 0){
                map.put("msg", "이메일 인증 미완료");
                response= new ResponseEntity(map, HttpStatus.BAD_REQUEST);
                return response;
            }
            map.put("msg", "로그인 성공");
            map.put("status", "success");
            map.put("email", email);
            String token = jwtService.generateToken(email);
            map.put("token", token);

            response = new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("status", "fail");
            map.put("msg", "로그인 실패");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @ApiOperation("로그아웃")
    @PostMapping("/logout")
    public Object logout(){
        return null;
    }

    @ApiOperation("이메일 인증")
    @GetMapping("/auth/{code}")
    public Object authEmail(@PathVariable("code") String code, HttpServletResponse res) throws Exception{
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        String email = base64Service.decode(code);

        User user = userService.findUserByEmail(email);

        if(user != null){
            user.updateUserAuth();
            map.put("msg", "인증 성공");
            map.put("status", "success");
            response = new ResponseEntity(map, HttpStatus.OK);
            res.sendRedirect("/");
        }else{
            map.put("msg", "인증 성공");
            map.put("status", "success");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

}
