package com.bigdata.tikitacar.user.entity;

import com.bigdata.tikitacar.user.dto.request.UserModifyRequestDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {
    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "address_detail")
    private String addressDetail;

    @Column(name = "auth")
    @ColumnDefault("0")
    private int auth;

    public void updateUserAuth(){
        this.auth = 1;
    }

    public void updateUserInfo(UserModifyRequestDto userModifyRequestDto){
        this.password = userModifyRequestDto.getPassword();
        this.nickname = userModifyRequestDto.getNickname();
        this.birth = userModifyRequestDto.getBirth();
        this.gender = userModifyRequestDto.getGender();
        this.phone = userModifyRequestDto.getPhone();
        this.address = userModifyRequestDto.getAddress();
        this.addressDetail = userModifyRequestDto.getAddressDetail();
    }

    public void randomPassword(String random){
        password = random;
    }
}
