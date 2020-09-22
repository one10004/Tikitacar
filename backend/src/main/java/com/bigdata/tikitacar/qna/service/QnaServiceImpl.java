package com.bigdata.tikitacar.qna.service;

import com.bigdata.tikitacar.car.entity.Deal;
import com.bigdata.tikitacar.car.repository.DealRepository;
import com.bigdata.tikitacar.exception.save.SaveFailException;
import com.bigdata.tikitacar.qna.dto.request.QnaWriteRequestDto;
import com.bigdata.tikitacar.qna.entity.Qna;
import com.bigdata.tikitacar.qna.repository.QnaRepository;
import com.bigdata.tikitacar.user.entity.User;
import com.bigdata.tikitacar.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QnaServiceImpl implements QnaService{

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
                .build();

        try{
            qnaRepository.save(qna);
        } catch (Exception e){
            throw new SaveFailException("(qna 작성 중) qna 저장 중에 오류 발생.");
        }
    }
}
