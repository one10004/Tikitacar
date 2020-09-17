package com.bigdata.tikitacar.car.controller;

import com.bigdata.tikitacar.car.dto.request.DealRegisterRequestDto;
import com.bigdata.tikitacar.car.dto.request.DealUpdateRequestDto;
import com.bigdata.tikitacar.car.dto.response.DealSearchResponseDto;
import com.bigdata.tikitacar.car.service.DealService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/deal")
public class DealController {

    @Autowired
    DealService dealService;


    //Create
    @ApiOperation("거래 등록")
    @PostMapping("")
    public Object dealRegister(@RequestHeader(value="Authorization") String token,
                               @RequestBody DealRegisterRequestDto dealRegisterRequestDto){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        //
        Long sellerId = token.();

        dealRegisterRequestDto.updateSellerId(sellerId);
        dealService.registerDeal(dealRegisterRequestDto);

        response = new ResponseEntity(map, HttpStatus.OK);
        return response;
    }


    //Read
    @ApiOperation("거래 한개 조회")
    @GetMapping("/{id}")
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
    @PutMapping("/{id}")
    public Object dealUpdate(@PathVariable Long id, @RequestBody DealUpdateRequestDto dealUpdateRequestDto){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        dealService.updateDeal(id,dealUpdateRequestDto);
        response = new ResponseEntity(map,HttpStatus.OK);

        return response;
    }


    //Delete
    @ApiOperation("거래 삭제")
    @DeleteMapping("/{id}")
    public Object dealDelete(@PathVariable Long id,@RequestBody DealUpdateRequestDto dealUpdateRequestDto){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String, Object>();

        dealService.updateDeal(id,dealUpdateRequestDto);
        response = new ResponseEntity(map,HttpStatus.OK);

        return response;
    }

}
