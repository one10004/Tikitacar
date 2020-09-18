package com.bigdata.tikitacar.user.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserLoginResponseDto {
    private String email;
    private String nickname;
    private Long id;
    private int auth;
}
