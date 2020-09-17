package com.bigdata.tikitacar.car.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Car {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

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

    //중고차 가격 예측을 위한 옵션 개수
    private int option;
}
