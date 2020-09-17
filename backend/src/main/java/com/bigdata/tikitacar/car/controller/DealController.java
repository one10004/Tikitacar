package com.bigdata.tikitacar.car.controller;

import com.bigdata.tikitacar.car.service.DealService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/deal")
public class DealController {

    @Autowired
    DealService dealService;


    //Create
    @ApiOperation("거래 등록")
    @PostMapping("")
    public Object dealRegister(){
        return null;
    }


    //Read
    @ApiOperation("거래 한개 조회")
    @GetMapping("/{id}")
    public Object dealGet(){
        return null;
    }

    @ApiOperation("모든 거래 등록")
    @GetMapping("")
    public Object dealGetAll(){
        return null;
    }



    //Update
    @ApiOperation("거래 업데이트")
    @PutMapping("/{id}")
    public Object dealUpdate(){
        return null;
    }


    //Delete
    @ApiOperation("거래 삭제")
    @DeleteMapping("/{id}")
    public Object dealDelete(){
        return null;
    }

}
