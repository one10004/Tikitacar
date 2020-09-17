package com.bigdata.tikitacar.user.controller;

import com.bigdata.tikitacar.user.dto.request.UserRequestDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {

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

}
