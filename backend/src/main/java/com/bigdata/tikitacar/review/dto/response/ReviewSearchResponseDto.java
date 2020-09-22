package com.bigdata.tikitacar.review.dto.response;

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
    private Deal deal;
    private User writer;
    private String title;
    private String content;
    private LocalDateTime date;
    private int rating;
}