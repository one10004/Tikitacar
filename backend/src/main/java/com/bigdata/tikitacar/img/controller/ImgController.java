package com.bigdata.tikitacar.img.controller;

import com.bigdata.tikitacar.img.dto.request.ImgUploadRequestDto;
import com.bigdata.tikitacar.img.service.ImgService;
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
@RequestMapping("/img")
public class ImgController {

    @Autowired
    ImgService imgService;

    @ApiOperation("사진 등록")
    @PostMapping("")
    public Object registerImg(@ModelAttribute ImgUploadRequestDto imgUploadRequestDto){
        ResponseEntity response = null;
        Map<String,Object> map=new HashMap<>();

        List<String> src = imgService.uploadImg(imgUploadRequestDto);

        if(src!=null && src.size()!=0){
            map.put("msg","이미지 등록에 성공했습니다.");
            map.put("status","success");
            map.put("src",src);
            response = new ResponseEntity(map, HttpStatus.OK);
        }else {
            map.put("msg", "레시피 등록에 실패했습니다.");
            map.put("status", "fail");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

}
