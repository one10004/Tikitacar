package com.bigdata.tikitacar.review.dto.response;

import com.bigdata.tikitacar.car.dto.response.DealSearchResponseDto;
import com.bigdata.tikitacar.car.entity.Deal;
import com.bigdata.tikitacar.user.entity.User;
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
    private Deal deal;

    //후기정보
    private String title;
    private String content;
    private LocalDateTime date;
    private int rating;
}