package com.bigdata.tikitacar.user.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequestDto {
    private Long id;
    private String password;
}
