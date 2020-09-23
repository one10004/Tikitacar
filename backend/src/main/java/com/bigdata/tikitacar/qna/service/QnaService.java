package com.bigdata.tikitacar.qna.service;

import com.bigdata.tikitacar.qna.dto.request.QnaWriteRequestDto;
import com.bigdata.tikitacar.qna.dto.response.QnaListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface QnaService {

    // Create
    public void registerQna(QnaWriteRequestDto qnaWriteRequestDto, String email);

    // Read
    public Map<String, Object> readQnas(Long id, Pageable pageable);
}
