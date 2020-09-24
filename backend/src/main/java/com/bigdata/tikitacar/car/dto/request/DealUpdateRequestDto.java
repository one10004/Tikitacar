package com.bigdata.tikitacar.car.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealUpdateRequestDto {

    Long id;
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

    String title;
    String content;

    String[] src;
}
