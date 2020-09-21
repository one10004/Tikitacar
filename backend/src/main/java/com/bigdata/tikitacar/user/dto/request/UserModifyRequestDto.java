package com.bigdata.tikitacar.user.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModifyRequestDto {
    private Long id;
    private String password;
    private String nickname;
    private LocalDate birth;
    private String gender;
    private String phone;
    private String address;
    private String addressDetail;
}

