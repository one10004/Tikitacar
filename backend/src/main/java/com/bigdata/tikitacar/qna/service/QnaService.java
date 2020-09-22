package com.bigdata.tikitacar.qna.service;

import com.bigdata.tikitacar.qna.dto.request.QnaWriteRequestDto;

public interface QnaService {

    // Create
    public void registerQna(QnaWriteRequestDto qnaWriteRequestDto, String email);
}
