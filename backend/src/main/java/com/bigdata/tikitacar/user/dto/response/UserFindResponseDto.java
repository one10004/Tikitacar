package com.bigdata.tikitacar.user.dto.response;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserFindResponseDto {
    private Long id;
    private String email;
    private String nickname;
    private LocalDate birth;
    private String gender;
    private String phone;
    private String address;
    private String addressDetail;
}
