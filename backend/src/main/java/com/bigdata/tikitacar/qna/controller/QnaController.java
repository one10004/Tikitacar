package com.bigdata.tikitacar.qna.controller;

import com.bigdata.tikitacar.qna.dto.request.QnaWriteRequestDto;
import com.bigdata.tikitacar.qna.dto.response.QnaListResponseDto;
import com.bigdata.tikitacar.qna.entity.Qna;
import com.bigdata.tikitacar.qna.service.QnaService;
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

    @ApiOperation("deal 거래에 해당하는 qna 목록 불러오기")
    @GetMapping("/{id}/{page}")
    public Object readQnaList(@PathVariable Long id, @PathVariable int page){
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<>();

        Map<String ,Object> res = qnaService.readQnas(id, PageRequest.of(page, 5));

        map.put("msg", "qna 목록 불러오기 성공");
        map.put("status", "success");
        map.put("qnaList", res.get("list"));
        map.put("totalPage", res.get("totalPage"));

        return new ResponseEntity(map, HttpStatus.OK);
    }
}
