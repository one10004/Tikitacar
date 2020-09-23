package com.bigdata.tikitacar.qna.controller;

import com.bigdata.tikitacar.qna.dto.request.QnaQuestionUpdateRequestDto;
import com.bigdata.tikitacar.qna.dto.request.QnaReplyUpdateRequestDto;
import com.bigdata.tikitacar.qna.dto.request.QnaWriteRequestDto;
import com.bigdata.tikitacar.qna.service.QnaService;
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
@RequestMapping("/qna")
public class QnaController {

    @Autowired
    QnaService qnaService;

    @Autowired
    JwtService jwtService;

    @ApiOperation("qna 작성")
    @PostMapping("")
    public Object writeQna(@RequestBody QnaWriteRequestDto qnaWriteRequestDto, @RequestHeader(value = "Authorization") String token) {
        Map<String, Object> map = new HashMap<>();

        String email = jwtService.getEmailFromToken(token.substring(7));

        qnaService.registerQna(qnaWriteRequestDto, email);

        map.put("msg", "qna 작성 성공");
        map.put("status", "success");

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @ApiOperation("deal 거래에 해당하는 qna 목록 불러오기")
    @GetMapping("/{id}/{page}")
    public Object readQnaList(@PathVariable Long id, @PathVariable int page) {
        Map<String, Object> map = new HashMap<>();

        Map<String, Object> res = qnaService.readQnas(id, PageRequest.of(page, 5));

        map.put("msg", "qna 목록 불러오기 성공");
        map.put("status", "success");
        map.put("qnaList", res.get("list"));
        map.put("totalPage", res.get("totalPage"));

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @ApiOperation("질문 삭제")
    @DeleteMapping("/{id}")
    public Object deleteQuestion(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();

        qnaService.removeQuestion(id);

        map.put("msg", "질문 삭제 성공");
        map.put("status", "success");

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @ApiOperation("질문 수정")
    @PutMapping("/{id}")
    public Object modifyQuestion(@PathVariable Long id, @RequestBody QnaQuestionUpdateRequestDto qnaQuestionUpdateRequestDto) {
        Map<String, Object> map = new HashMap<>();

        qnaService.modifyQuestion(id, qnaQuestionUpdateRequestDto);

        map.put("msg", "질문 수정 성공");
        map.put("status", "success");

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @ApiOperation("답글 달기(수정)")
    @PutMapping("/reply/{id}")
    public Object replyToQuestion(@PathVariable Long id, @RequestBody QnaReplyUpdateRequestDto qnaReplyUpdateRequestDto) {
        Map<String, Object> map = new HashMap<>();

        qnaService.replyToQuestion(id, qnaReplyUpdateRequestDto);

        map.put("msg", "질문에 답변 달기(또는 수정) 성공");
        map.put("status", "success");

        return new ResponseEntity(map, HttpStatus.OK);
    }

    @ApiOperation("답변 삭제")
    @DeleteMapping("/reply/{id}")
    public Object modifyReply(@PathVariable Long id){
        Map<String, Object> map = new HashMap<>();

        qnaService.removeReply(id);

        map.put("msg", "답변 삭제 성공");
        map.put("status", "success");

        return new ResponseEntity(map, HttpStatus.OK);
    }
}
