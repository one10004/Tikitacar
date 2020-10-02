package com.bigdata.tikitacar.car.repository;

import com.bigdata.tikitacar.car.dto.request.DealSearchRequestDto;
import com.bigdata.tikitacar.car.dto.response.DealSearchResponseDto;
import com.bigdata.tikitacar.car.service.DealService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DealRepositoryImplTest {

    @Autowired
    DealRepository dealRepository;

    @Autowired
    DealService dealService;

    @Test
    public void test(){
        DealSearchRequestDto dealSearchRequestDto = DealSearchRequestDto.builder()
                .name("없음")
                .year1(-1)
                .year2(-1)
                .cc(-1)
                .distance(-1)
                .color("없음")
                .fuel("없음")
                .gear("없음")
                .flooding(-1)
                .insurance(-1)
                .price(-1)
                .seat(-1)
                .status("판매완료")
                .build();

//        List<DealSearchResponseDto> dealSearchResponseDtoList = dealRepository.selectDealList(dealSearchRequestDto);
        List<DealSearchResponseDto> dealSearchResponseDtoList = dealService.searchDetail(dealSearchRequestDto);
        System.out.println(dealSearchResponseDtoList.size());
        for (DealSearchResponseDto dealSearchResponseDto : dealSearchResponseDtoList) {
            System.out.println(">>>>"+dealSearchResponseDto.toString());
        }
    }

}