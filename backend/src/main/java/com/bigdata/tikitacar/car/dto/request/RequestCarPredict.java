package com.bigdata.tikitacar.car.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestCarPredict {
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

    //중고차 가격 예측을 위한 옵션 개수
    private int option;
}
