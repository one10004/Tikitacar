package com.bigdata.tikitacar.qna.service;

import com.bigdata.tikitacar.qna.dto.request.QnaReplyUpdateRequestDto;
import com.bigdata.tikitacar.qna.dto.request.QnaWriteRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface QnaService {

    // Create
    public void registerQna(QnaWriteRequestDto qnaWriteRequestDto, String email);

    // Read
    public Map<String, Object> readQnas(Long id, Pageable pageable);

    // Update
    public void replyToQuestion(Long id, QnaReplyUpdateRequestDto qnaReplyUpdateRequestDto);

    // Delete
    public void removeQuestion(Long id);
}
