package com.bigdata.tikitacar.car.repository;

import com.bigdata.tikitacar.car.dto.request.DealSearchRequestDto;
import com.bigdata.tikitacar.car.dto.response.DealSearchResponseDto;

import java.util.List;

public interface DealRepositoryMybatis {
    List<DealSearchResponseDto> selectDealList(DealSearchRequestDto dealSearchRequestDto);
}
