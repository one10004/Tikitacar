package com.bigdata.tikitacar.review.controller;

import com.bigdata.tikitacar.review.dto.request.ReviewRegisterRequestDto;
import com.bigdata.tikitacar.review.dto.request.ReviewUpdateRequestDto;
import com.bigdata.tikitacar.review.dto.response.ReviewSearchResponseDto;
import com.bigdata.tikitacar.review.service.ReviewService;
import com.bigdata.tikitacar.user.service.UserService;
import com.bigdata.tikitacar.util.JwtService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    //List
    @ApiOperation("후기 리스트 조회")
    @GetMapping("/all/{page}")
    public Object ReviewGetAll(@PathVariable("page") int page){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        Map<String, Object> resultData = reviewService.searchAllReview(PageRequest.of(page, 10));

        map.put("msg","후기 리스트 조회에 성공했습니다.");
        map.put("status","success");
        map.put("reviewSearchResponseDtoList", resultData.get("reviewSearchResponseDtoList"));
        map.put("totalPage", resultData.get("totalPage"));
        response = new ResponseEntity(map,HttpStatus.OK);

        return response;
    }

    //Read
    @ApiOperation("후기 한개 조회")
    @GetMapping("/view/{id}")
    public Object ReviewGet(@PathVariable Long id){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        ReviewSearchResponseDto reviewSearchResponseDto = reviewService.searchReview(id);

        map.put("msg","후기 조회에 성공했습니다.");
        map.put("status","success");
        map.put("data",reviewSearchResponseDto);
        response = new ResponseEntity(map,HttpStatus.OK);

        return response;
    }

    //Update
    @ApiOperation("후기 수정")
    @PutMapping("")
    public Object reviewUpdate(@RequestHeader(value="Authorization") String token,
                                 @RequestBody ReviewUpdateRequestDto reviewUpdateRequestDto){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        reviewUpdateRequestDto.updateWriter(userService.findUserByEmail(jwtService.getEmailFromToken(token)).getId());
        reviewService.updateReview(reviewUpdateRequestDto);

        map.put("msg","후기 수정에 성공했습니다.");
        map.put("status","success");
        response = new ResponseEntity(map, HttpStatus.OK);
        return response;
    }

    //Delete
    @ApiOperation("후기 삭제")
    @DeleteMapping("/{id}")
    public Object reviewDelete(@RequestHeader(value="Authorization") String token,
                               @PathVariable Long id){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        String loginEmail = jwtService.getEmailFromToken(token);
        ReviewSearchResponseDto reviewSearchResponseDto = reviewService.searchReview(id);

        if(loginEmail != null && loginEmail.equals(reviewSearchResponseDto.getEmail())){
            reviewService.removeReview(id);
            map.put("msg","후기 삭제에 성공했습니다.");
            map.put("status","success");
            response = new ResponseEntity(map,HttpStatus.OK);

        }else{
            map.put("msg","후기 삭제에 실패했습니다.");
            map.put("status","fail");
            response = new ResponseEntity(map,HttpStatus.BAD_REQUEST);
        }
        return response;
    }

}
