package com.bigdata.tikitacar.user.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDeleteCheckRequsetDto {
    String token;
    String password;
}
