package com.bigdata.tikitacar.car.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="car_price")
public class CarPrice {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "company")
    private String company;

    @Column(name = "model")
    private String model;

    @Column(name = "price_low")
    private int priceLow;

    @Column(name = "price_high")
    private int priceHigh;
}
