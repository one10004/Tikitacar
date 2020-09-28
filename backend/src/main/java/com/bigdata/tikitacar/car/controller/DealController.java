package com.bigdata.tikitacar.car.controller;

import com.bigdata.tikitacar.car.dto.request.DealRegisterRequestDto;
import com.bigdata.tikitacar.car.dto.request.DealUpdateRequestDto;
import com.bigdata.tikitacar.car.dto.request.DealUpdateStatusRequestDto;
import com.bigdata.tikitacar.car.dto.response.DealDetailResponseDto;
import com.bigdata.tikitacar.car.service.DealService;
import com.bigdata.tikitacar.user.service.UserService;
import com.bigdata.tikitacar.util.JwtService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/deal")
public class DealController {

    @Autowired
    DealService dealService;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    //Create
    @ApiOperation("거래 등록")
    @PostMapping("")
    public Object dealRegister(@RequestHeader(value="Authorization") String token,
                               @RequestBody DealRegisterRequestDto dealRegisterRequestDto){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        String loginEamil = jwtService.getEmailFromToken(token);

        if(loginEamil!=null){
            //유저정보 dto에 등록
            dealRegisterRequestDto.updateSellerId(userService.findUserByEmail(loginEamil).getId());
            dealService.registerDeal(dealRegisterRequestDto);

            map.put("msg","거래 등록에 성공했습니다.");
            map.put("status","success");
            response = new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("msg","거래 등록에 실패했습니다.");
            map.put("status","fail");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }


        return response;
    }


    //Read
    @ApiOperation("거래 한개 조회")
    @GetMapping("/view/{id}")
    public Object dealGet(@PathVariable Long id){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        DealDetailResponseDto dealDetailResponseDto = dealService.searchDeal(id);

        map.put("msg","거래 조회에 성공했습니다.");
        map.put("status","success");
        map.put("data", dealDetailResponseDto);
        response = new ResponseEntity(map,HttpStatus.OK);

        return response;
    }


    //Update
    @ApiOperation("거래 업데이트")
    @PutMapping("")
    public Object dealUpdate(@RequestHeader(value="Authorization") String token,
                             @RequestBody DealUpdateRequestDto dealUpdateRequestDto){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        String loginEmail = jwtService.getEmailFromToken(token);
        DealDetailResponseDto dealDetailResponseDto = dealService.searchDeal(dealUpdateRequestDto.getId());

        if(loginEmail!= null && loginEmail.equals(dealDetailResponseDto.getEmail())){
            dealService.updateDeal(dealUpdateRequestDto);
            map.put("msg","거래 수정에 성공했습니다.");
            map.put("status","success");
            response = new ResponseEntity(map,HttpStatus.OK);
        }else{
            map.put("msg","거래 수정에 실패하였습니다.");
            map.put("status","fail");
            response = new ResponseEntity(map,HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @ApiOperation("거래 완료처리")
    @PutMapping("/status/{id}")
    public Object dealDone(@RequestHeader(value="Authorization") String token,
                           @PathVariable("id") Long dealId,
                           @RequestBody DealUpdateStatusRequestDto dealUpdateStatusRequestDto){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        //현재 로그인한 아이디
        String loginEmail = jwtService.getEmailFromToken(token);
        DealDetailResponseDto dealDetailResponseDto = dealService.searchDeal(dealId);

        if(loginEmail!=null && loginEmail.equals(dealDetailResponseDto.getEmail())){
            dealService.updateDealStatus(dealId,dealUpdateStatusRequestDto);
            map.put("msg","거래 완료에 성공했습니다.");
            map.put("status","success");
            response = new ResponseEntity(map,HttpStatus.OK);
        }else{
            map.put("msg","거래 완료에 실패했습니다.");
            map.put("status","fail");
            response = new ResponseEntity(map,HttpStatus.BAD_REQUEST);
        }
        return response;
    }


    //Delete
    @ApiOperation("거래 삭제")
    @DeleteMapping("/{id}")
    public Object dealDelete(@RequestHeader(value="Authorization") String token,
                             @PathVariable Long id){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        String loginEmail = jwtService.getEmailFromToken(token);
        DealDetailResponseDto dealDetailResponseDto = dealService.searchDeal(id);

        if(loginEmail!= null && loginEmail.equals(dealDetailResponseDto.getEmail())){
            dealService.removeDeal(id);
            map.put("msg","거래 삭제에 성공했습니다.");
            map.put("status","success");
            response = new ResponseEntity(map,HttpStatus.OK);

        }else{
            map.put("msg","거래 삭제에 실패했습니다.");
            map.put("status","fail");
            response = new ResponseEntity(map,HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    //GetAll
    @ApiOperation("거래 리스트 조회")
    @GetMapping("/all/{page}")
    public Object dealGetAll(@PathVariable("page") int page){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        List<DealDetailResponseDto> dealDetailResponseDtoList = dealService.searchAll(PageRequest.of(page, 10));

        map.put("data", dealDetailResponseDtoList);
        response = new ResponseEntity(map,HttpStatus.OK);

        return response;
    }

}
