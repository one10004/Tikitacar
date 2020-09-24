package com.bigdata.tikitacar.car.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealUpdateStatusRequestDto {
    String email;
    String nickname;
}
