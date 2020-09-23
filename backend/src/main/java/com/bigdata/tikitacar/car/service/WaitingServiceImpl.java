package com.bigdata.tikitacar.car.service;

import com.bigdata.tikitacar.car.dto.response.WaitingUserResponseDto;
import com.bigdata.tikitacar.car.entity.Deal;
import com.bigdata.tikitacar.car.entity.Waiting;
import com.bigdata.tikitacar.car.repository.DealRepository;
import com.bigdata.tikitacar.car.repository.WaitingRepository;
import com.bigdata.tikitacar.user.entity.User;
import com.bigdata.tikitacar.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WaitingServiceImpl  implements WaitingService{
    @Autowired
    WaitingRepository waitingRepository;

    @Autowired
    DealRepository dealRepository;
    
    @Autowired
    UserRepository userRepository;
    
    
    @Override
    public List<WaitingUserResponseDto> searchWaitingUser(Long dealId) {
        List<WaitingUserResponseDto> list =new ArrayList<>();

        List<User> users = waitingRepository.findByDeal_Id(dealId);

        for(User u :users){
            list.add(WaitingUserResponseDto.builder()
                    .id(u.getId())
                    .email(u.getEmail())
                    .nickname(u.getNickname())
                    .build());
        }
        return list;
    }

    @Override
    public void registerWaiting(Long dealId, Long buyerId) {

        Deal deal = Optional.of(dealRepository.findById(dealId))
                .orElseThrow(() -> new NoSuchElementException("거래가 존재하지 않음.")).get();
        User buyer = Optional.of(userRepository.findById(buyerId))
                .orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않음.")).get();

        if(deal.getSeller().getId() == buyerId){    //같으면 익셉션
            throw new SaveFailException("자신의 게시물 입니다.");
        }

        Waiting waiting= Waiting.builder()
                .deal(deal)
                .buyer(buyer)
                .build();

        try {
            waitingRepository.save(waiting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWaiting(Long dealId, Long buyerId) {
        Waiting waiting= Optional.of(waitingRepository.findByDeal_IdAndBuyer_Id(dealId,buyerId))
                .orElseThrow(() -> new NoSuchElementException("대기가 존재하지 않음.")).get();

        try {
            waitingRepository.delete(waiting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
