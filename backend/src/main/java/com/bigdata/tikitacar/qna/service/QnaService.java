package com.bigdata.tikitacar.qna.service;

import com.bigdata.tikitacar.qna.dto.request.QnaWriteRequestDto;
import com.bigdata.tikitacar.qna.dto.response.QnaListResponseDto;

import java.util.List;

public interface QnaService {

    // Create
    public void registerQna(QnaWriteRequestDto qnaWriteRequestDto, String email);

    // Read
    public List<QnaListResponseDto> readQnas(Long id);
}
