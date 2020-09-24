package com.bigdata.tikitacar.user.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserFindPasswordRequestDto {
    String email;
}
