package com.bigdata.tikitacar.user.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginRequestDto {
    private String email;
    private String password;
}
