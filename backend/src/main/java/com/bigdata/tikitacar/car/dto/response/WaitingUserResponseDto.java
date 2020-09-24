package com.bigdata.tikitacar.car.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaitingUserResponseDto {

    Long id;
    String email;
    String nickname;
}
