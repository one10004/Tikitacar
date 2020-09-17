package com.bigdata.tikitacar.car.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealRegisterRequestDto {
    
    //유저정보
    Long sellerId;
    
    //차정보
    String name;
    int year;
    int cc;
    int distance;
    String color;
    String gear;
    String fuel;
    int seat;
    int flooding;
    int insurance;
    int releasePrice;
    int price;

    //글정보
    String title;
    String content;

}
