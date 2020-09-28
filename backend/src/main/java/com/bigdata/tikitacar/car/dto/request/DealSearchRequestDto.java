package com.bigdata.tikitacar.car.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealSearchRequestDto {
    private String name;
    private int year;
    private int cc;
    private int distance;
    private String color;
    private String gear;
    private String fuel;
    private int seat;
    private int flooding;
    private int insurance;
    private int releasePrice;
    private int price;

}
