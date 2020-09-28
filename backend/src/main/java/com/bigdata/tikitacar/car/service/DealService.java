package com.bigdata.tikitacar.car.service;

import com.bigdata.tikitacar.car.dto.request.DealRegisterRequestDto;
import com.bigdata.tikitacar.car.dto.request.DealUpdateRequestDto;
import com.bigdata.tikitacar.car.dto.request.DealUpdateStatusRequestDto;
import com.bigdata.tikitacar.car.dto.response.DealDetailResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DealService {

    //Create
    public void registerDeal(DealRegisterRequestDto dealRegisterRequestDto);

    //Read
    public DealDetailResponseDto searchDeal(Long id);
//    public List<DealSearchResponseDto> searchAllDeal();

    //Update
    public void updateDeal(DealUpdateRequestDto dealUpdateRequestDto);
    public void updateDealStatus(Long dealId, DealUpdateStatusRequestDto dealUpdateStatusRequestDto);


    //Delete
    public void removeDeal(Long id);

    //List
    List<DealDetailResponseDto> searchAll(Pageable pageable);
}
