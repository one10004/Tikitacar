package com.bigdata.tikitacar.user.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegisterRequestDto {
    private Long id;
    private String password;
    private String email;
    private String nickname;
    private Date birth;
    private String gender;
    private String phone;
    private String address;
    private String address_detail;
    private int auth;
}
