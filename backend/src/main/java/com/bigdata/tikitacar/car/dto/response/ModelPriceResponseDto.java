package com.bigdata.tikitacar.car.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ModelPriceResponseDto {
    int priceLow;
    int priceHigh;
}
