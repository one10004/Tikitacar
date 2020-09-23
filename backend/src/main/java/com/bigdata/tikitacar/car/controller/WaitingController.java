package com.bigdata.tikitacar.car.controller;

import com.bigdata.tikitacar.car.dto.request.DealRegisterRequestDto;
import com.bigdata.tikitacar.car.dto.response.DealSearchResponseDto;
import com.bigdata.tikitacar.car.dto.response.WaitingUserResponseDto;
import com.bigdata.tikitacar.car.service.DealService;
import com.bigdata.tikitacar.car.service.WaitingService;
import com.bigdata.tikitacar.user.service.UserService;
import com.bigdata.tikitacar.util.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/waiting")
public class WaitingController {

    @Autowired
    WaitingService waitingService;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    DealService dealService;

    @ApiOperation("대기 등록")
    @PostMapping("/{id}")
    public Object registerWaiting(@RequestHeader(value="Authorization") String token,
                                  @PathVariable("id") Long dealId){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        String loginEmail = jwtService.getEmailFromToken(token);

        if(loginEmail!=null){
            waitingService.registerWaiting(userService.findUserByEmail(loginEmail).getId(),dealId);

            map.put("msg","대기 등록에 성공했습니다.");
            map.put("status","success");
            response = new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("msg","대기 등록에 실패했습니다.");
            map.put("status","fail");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @ApiOperation("대기 조회")
    @GetMapping("/{id}")
    public Object searchWaitingList(@RequestHeader(value="Authorization") String token,
                                  @PathVariable("id") Long dealId){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        DealSearchResponseDto dealSearchResponseDto = dealService.searchDeal(dealId);
        String loginEmail=jwtService.getEmailFromToken(token);


        //현재로그인한의 이메일과 글의 작성자 이메일과 같은 경우만 가능
        if(loginEmail!= null && dealSearchResponseDto.getEmail().equals(loginEmail)){
            List<WaitingUserResponseDto> list = waitingService.searchWaitingUser(dealId);
            map.put("msg","대기 유저 조회에 성공했습니다.");
            map.put("status","success");
            map.put("list",list);
            response = new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("msg","대기 유저 조회에 실패하였습니다.");
            map.put("status","fail");
            response = new ResponseEntity(map,HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @ApiOperation("대기 취소")
    @DeleteMapping("/{id}")
    public Object deleteWaitingList(@@RequestHeader(value="Authorization") String token,
                                    @PathVariable("id") Long dealId){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        DealSearchResponseDto dealSearchResponseDto = dealService.searchDeal(dealId);
        String loginEmail=jwtService.getEmailFromToken(token);
        if(loginEmail!=null){
            waitingService.deleteWaiting(dealId,userService.findUserByEmail(loginEmail).getId());
            map.put("msg","대기 삭제에 성공하였습니다.");
            map.put("status","success");
            response = new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("msg","대기 삭제에 실패하였습니다.");
            map.put("status","fail");
            response = new ResponseEntity(map,HttpStatus.BAD_REQUEST);
        }
        return response;

    }


}
