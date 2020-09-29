package com.bigdata.tikitacar.car.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealSearchResponseDto {

    Long id;

    //유저정보
    String nickname;


    //차정보
    String name;
    int releasePrice;
    int price;

    //글정보
    String title;

    //이미지
    String src;

    //상태
    String status;
}
