package com.bigdata.tikitacar.user.dto.request;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegisterRequestDto {
    private String password;
    private String email;
    private String nickname;
    private LocalDate birth;
    private String gender;
    private String phone;
    private String address;
    private String addressDetail;
    private int auth;
}
