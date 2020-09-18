package com.bigdata.tikitacar.user.controller;

import com.bigdata.tikitacar.user.dto.request.UserRequestDto;
import com.bigdata.tikitacar.user.entity.User;
import com.bigdata.tikitacar.user.service.UserService;
import com.bigdata.tikitacar.util.Base64Service;
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

    @ApiOperation("로그인")
    @PostMapping("/login")
    public Object login(@RequestBody UserRequestDto userRequestDto){
        return null;
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
