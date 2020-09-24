package com.bigdata.tikitacar.qna.service;

import com.bigdata.tikitacar.car.entity.Deal;
import com.bigdata.tikitacar.car.repository.DealRepository;
import com.bigdata.tikitacar.exception.custom.SaveFailException;
import com.bigdata.tikitacar.qna.dto.request.QnaQuestionUpdateRequestDto;
import com.bigdata.tikitacar.qna.dto.request.QnaReplyUpdateRequestDto;
import com.bigdata.tikitacar.qna.dto.request.QnaWriteRequestDto;
import com.bigdata.tikitacar.qna.dto.response.QnaListResponseDto;
import com.bigdata.tikitacar.qna.entity.Qna;
import com.bigdata.tikitacar.qna.repository.QnaRepository;
import com.bigdata.tikitacar.user.entity.User;
import com.bigdata.tikitacar.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class QnaServiceImpl implements QnaService {

    @Autowired
    private QnaRepository qnaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DealRepository dealRepository;

    @Override
    public void registerQna(QnaWriteRequestDto qnaWriteRequestDto, String email) {
        User user = Optional.of(userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("(qna 작성중) 이메일에 해당하는 유저가 존재하지 않음."))).get();

        Deal deal = Optional.of(dealRepository.findById(qnaWriteRequestDto.getDeal())
                .orElseThrow(() -> new NoSuchElementException("(qna 작성 중) id에 해당하는 Deal 정보가 존재하지 않음."))).get();

        Qna qna = Qna.builder()
                .deal(deal)
                .writer(user)
                .content(qnaWriteRequestDto.getContent())
                .regTime(LocalDateTime.now())
                .build();

        try {
            qnaRepository.save(qna);
        } catch (Exception e) {
            throw new SaveFailException("(qna 작성 중) qna 저장 중에 오류 발생.");
        }
    }

    @Override
    public Map<String, Object> readQnas(Long id, Pageable pageable) {

        Map<String, Object> map = new HashMap<>();
        Page<Qna> qnaList = qnaRepository.findQnaByDealNumber(id, pageable);
        List<QnaListResponseDto> qnaListResponseDtoList = new ArrayList<>();

        map.put("totalPage", qnaList.getTotalPages());

        for (Qna qna : qnaList) {
            User user = Optional.of(userRepository.findById(qna.getWriter().getId())
                    .orElseThrow(() -> new NoSuchElementException("(qna 목록 불러오기 중) user id에 해당하는 유저 존재하지 않음."))).get();

            QnaListResponseDto qnaListResponseDto = QnaListResponseDto.builder()
                    .id(qna.getId())
                    .nickname(user.getNickname())
                    .content(qna.getContent())
                    .reply(qna.getReply())
                    .regTime(qna.getRegTime())
                    .build();

            qnaListResponseDtoList.add(qnaListResponseDto);
        }

        map.put("list", qnaListResponseDtoList);
        return map;
    }

    @Override
    @Transactional
    public void replyToQuestion(Long id, QnaReplyUpdateRequestDto qnaReplyUpdateRequestDto) {
        Qna qna = Optional.of(qnaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("(질문 답변 작성(수정) 중) id에 해당하는 질문을 찾을 수 없음."))).get();

        try {
            qna.updateReply(qnaReplyUpdateRequestDto.getReply());
        } catch (Exception e) {
            throw new SaveFailException("(질문 답변 작성(수정) 중) 답변 달고(수정 후) 저장 중 오류 발생.");
        }

    }

    @Override
    @Transactional
    public void modifyQuestion(Long id, QnaQuestionUpdateRequestDto qnaQuestionUpdateRequestDto) {
        Qna qna = Optional.of(qnaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("(질문 수정 중) id에 해당 하는 질문을 찾을 수 없음."))).get();

        if (qna.getReply() != null)
            throw new SaveFailException("(질문 수정 중) 질문에 답변이 있어서 수정할 수 없음");

        try {
            qna.updateQuestion(qnaQuestionUpdateRequestDto.getContent());
        } catch (Exception e) {
            throw new SaveFailException("(질문 수정 중) 질문 수정 후 저장 중 오류 발생.");
        }
    }

    @Override
    @Transactional
    public void removeReply(Long id) {
        Qna qna = Optional.of(qnaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("(답변 삭제 중) id에 해당 하는 질문을 찾을 수 없음."))).get();

        if(qna.getReply() == null)
            throw new SaveFailException("(답변 삭제 중) 답변이 없어서 삭제할 수 없음");

        try {
            qna.deleteReply();
        } catch (Exception e){
            throw new SaveFailException("(답변 삭제 중) 답변 삭제 중 오류 발생");
        }
    }

    @Override
    @Transactional
    public void removeQuestion(Long id) {
        try {
            qnaRepository.deleteById(id);
        } catch (Exception e) {
            throw new SaveFailException("(질문 삭제 중) 질문 삭제 중 오류 발생");
        }
    }
}
