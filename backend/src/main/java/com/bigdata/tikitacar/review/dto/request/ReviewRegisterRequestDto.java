package com.bigdata.tikitacar.review.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewRegisterRequestDto {
    private Long id;
    private String title;
    private String content;
    private int rating;
}
