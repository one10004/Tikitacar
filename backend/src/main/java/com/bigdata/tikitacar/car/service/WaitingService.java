package com.bigdata.tikitacar.car.service;

import com.bigdata.tikitacar.car.dto.response.WaitingUserResponseDto;

import java.util.List;

public interface WaitingService {
    public List<WaitingUserResponseDto> searchWaitingUser(Long dealId);
    public void registerWaiting( Long dealId, Long buyerId);

    public void deleteWaiting(Long dealId,Long buyerId);

}
