package com.bigdata.tikitacar.car.repository;

import com.bigdata.tikitacar.car.dto.request.DealSearchRequestDto;
import com.bigdata.tikitacar.car.dto.response.DealSearchResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DealRepositoryImplTest {

    @Autowired
    DealRepository dealRepository;

    @Test
    public void test(){
        DealSearchRequestDto dealSearchRequestDto = DealSearchRequestDto.builder()
                .cc(-1)
                .color("없음")
                .distance(-1)
                .flooding(-1)
                .fuel("없음")
                .gear("없음")
                .insurance(-1)
                .name("없음")
                .price(-1)
                .seat(-1)
                .status("판매완료")
                .year1(-1)
                .year2(-1)
                .build();

        List<DealSearchResponseDto> dealSearchResponseDtoList = dealRepository.selectDealList(dealSearchRequestDto);
        for (DealSearchResponseDto dealSearchResponseDto : dealSearchResponseDtoList) {
            System.out.println(dealSearchRequestDto);
        }
    }

}