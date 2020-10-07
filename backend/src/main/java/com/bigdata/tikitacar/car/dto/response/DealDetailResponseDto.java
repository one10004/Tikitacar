package com.bigdata.tikitacar.car.dto.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DealDetailResponseDto {
    Long id;

    //유저정보
    String email;
    String nickname;
    String phone;
    String address;


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
    String status;

    List<String> src;

}
