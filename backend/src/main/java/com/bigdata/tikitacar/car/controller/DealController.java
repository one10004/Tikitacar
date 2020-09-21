package com.bigdata.tikitacar.car.controller;

import com.bigdata.tikitacar.car.dto.request.DealRegisterRequestDto;
import com.bigdata.tikitacar.car.dto.request.DealUpdateRequestDto;
import com.bigdata.tikitacar.car.dto.response.DealSearchResponseDto;
import com.bigdata.tikitacar.car.service.DealService;
import com.bigdata.tikitacar.user.entity.User;
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

        //유저정보 dto에 등록
        dealRegisterRequestDto.updateSellerId(userService.findUserByEmail(jwtService.getEmailFromToken(token)).getId());
        dealService.registerDeal(dealRegisterRequestDto);

        response = new ResponseEntity(map, HttpStatus.OK);
        return response;
    }


    //Read
    @ApiOperation("거래 한개 조회")
    @GetMapping("/view/{id}")
    public Object dealGet(@PathVariable Long id){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        DealSearchResponseDto dealSearchResponseDto = dealService.searchDeal(id);

        map.put("data",dealSearchResponseDto);
        response = new ResponseEntity(map,HttpStatus.OK);

        return response;
    }


    //Update
    @ApiOperation("거래 업데이트")
    @PutMapping("/")
    public Object dealUpdate(@RequestBody DealUpdateRequestDto dealUpdateRequestDto){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        dealService.updateDeal(dealUpdateRequestDto);
        response = new ResponseEntity(map,HttpStatus.OK);

        return response;
    }

    //Delete
    @ApiOperation("거래 삭제")
    @DeleteMapping("/{id}")
    public Object dealDelete(@PathVariable Long id){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        dealService.removeDeal(id);
        response = new ResponseEntity(map,HttpStatus.OK);

        return response;
    }

    //GetAll
    @ApiOperation("거래 리스트 조회")
    @GetMapping("/all/{page}")
    public Object dealGetAll(@PathVariable("page") int page){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        List<DealSearchResponseDto> dealSearchResponseDtoList = dealService.searchAll(PageRequest.of(page, 10));

        map.put("data",dealSearchResponseDtoList);
        response = new ResponseEntity(map,HttpStatus.OK);

        return response;
    }

}
