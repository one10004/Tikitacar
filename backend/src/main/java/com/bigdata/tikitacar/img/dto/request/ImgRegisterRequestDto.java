package com.bigdata.tikitacar.img.dto.request;

import com.bigdata.tikitacar.car.entity.Deal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImgRegisterRequestDto {
    private Deal deal;
    private String src;
}
