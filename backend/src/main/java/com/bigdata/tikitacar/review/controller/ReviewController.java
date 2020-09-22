package com.bigdata.tikitacar.review.controller;

import com.bigdata.tikitacar.review.dto.request.ReviewRegisterRequestDto;
import com.bigdata.tikitacar.review.service.ReviewService;
import com.bigdata.tikitacar.user.service.UserService;
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
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    //Create
    @ApiOperation("후기 등록")
    @PostMapping("")
    public Object reviewRegister(@RequestHeader(value="Authorization") String token,
                               @RequestBody ReviewRegisterRequestDto reviewRegisterRequestDto){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        reviewRegisterRequestDto.updateWriter(userService.findUserByEmail(jwtService.getEmailFromToken(token)).getId());
        reviewService.registerReview(reviewRegisterRequestDto);

        map.put("msg","후기 등록에 성공했습니다.");
        map.put("status","success");
        response = new ResponseEntity(map, HttpStatus.OK);
        return response;
    }

}
