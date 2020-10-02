package com.bigdata.tikitacar.review.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewSearchResponseDto {
    private Long id;

    //유저정보
    String email;
    String nickname;
    
    //거래정보
    String sellerEmail;
    String sellerNickname;
    String carName;
    int carPrice;
    String dealTitle;
    String dealContent;

    //후기정보
    private String title;
    private String content;
    private LocalDateTime date;
    private int rating;

    // 차 정보
    int year;
    int cc;
    int distance;
    String color;
    String gear;
    String fuel;
    int seat;
    int flooding;
    int insurance;
    String src;
}