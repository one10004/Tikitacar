package com.bigdata.tikitacar.car.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DealSearchResponseDto {

    Long id;

    //유저정보
    String nickname;


    //차정보
    String name;
    int releasePrice;
    int price;
    int year;
    int distance;
    String color;


    //글정보
    String title;

    //이미지
    String src;

    //상태
    String status;
}
