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
                .status("판매중")
                .year1(-1)
                .year2(-1)
                .build();

        List<DealSearchResponseDto> dealSearchResponseDtoList1 = dealService.searchDetail(dealSearchRequestDto);

        for (DealSearchResponseDto dealSearchResponseDto : dealSearchResponseDtoList1) {
            System.out.println(dealSearchResponseDto);
        }
    }

}