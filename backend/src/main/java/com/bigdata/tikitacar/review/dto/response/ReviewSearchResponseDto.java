package com.bigdata.tikitacar.review.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewSearchResponseDto {
    private Long id;
    private String title;
    private String content;
    private int rating;
}