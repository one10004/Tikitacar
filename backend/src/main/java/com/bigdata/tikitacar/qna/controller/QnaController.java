package com.bigdata.tikitacar.qna.controller;

import com.bigdata.tikitacar.qna.dto.request.QnaWriteRequestDto;
import com.bigdata.tikitacar.qna.service.QnaService;
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
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    QnaService qnaService;

    @Autowired
    JwtService jwtService;

    @ApiOperation("qna 작성")
    @PostMapping("")
    public Object writeQna(@RequestBody QnaWriteRequestDto qnaWriteRequestDto, @RequestHeader(value = "Authorization") String token) {
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        String email = jwtService.getEmailFromToken(token.substring(7));

        qnaService.registerQna(qnaWriteRequestDto, email);

        map.put("msg", "qna 작성 성공");
        map.put("status", "success");

        return new ResponseEntity(map, HttpStatus.OK);

    }
}
