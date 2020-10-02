package com.bigdata.tikitacar.car.controller;

import com.bigdata.tikitacar.car.dto.response.ModelPriceResponseDto;
import com.bigdata.tikitacar.car.service.PriceService;
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
@RequestMapping("/price")
public class CarPriceController {

    @Autowired
    PriceService priceService;

    @ApiOperation("제조사 목록")
    @GetMapping("")
    public Object companyList() {
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        List<String> companyList = priceService.readCompanyList();

        map.put("msg", "제조사 목록 불러오기 성공");
        map.put("status", "success");
        map.put("companyList", companyList);
        response = new ResponseEntity(map, HttpStatus.OK);

        return response;
    }

    @ApiOperation("제조사 별 모델 목록")
    @GetMapping("/{company}")
    public Object modelList(@PathVariable String company){
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        List<String> modelList = priceService.readModelList(company);

        map.put("msg", "제조사 별 모델 목록 불러오기 성공");
        map.put("status", "success");
        map.put("modelList", modelList);
        response = new ResponseEntity(map, HttpStatus.OK);

        return response;
    }

    @ApiOperation("모델 별 최저 가격과 최고 가격")
    @GetMapping("/model/{model}")
    public Object modelPrice(@PathVariable String model){
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        ModelPriceResponseDto modelPriceResponseDto = priceService.readModelPrice(model);

        map.put("msg", "모델 가격 불러오기 성공");
        map.put("status", "success");
        map.put("priceLow", modelPriceResponseDto.getPriceLow());
        map.put("priceHigh", modelPriceResponseDto.getPriceHigh());
        response = new ResponseEntity(map, HttpStatus.OK);

        return response;
    }
}
