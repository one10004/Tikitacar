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


    public void updateCar(String name,int year,int cc,int distance,String color,
                       String gear,String fuel,int seat,int flooding,
                       int insurance,int releasePrice,int price){
        this.name=name;
        this.year=year;
        this.cc=cc;
        this.distance=distance;
        this.color=color;
        this.gear=gear;
        this.fuel=fuel;
        this.seat=seat;
        this.flooding=flooding;
        this.insurance=insurance;
        this.releasePrice=releasePrice;
        this.price=price;
    }

}
