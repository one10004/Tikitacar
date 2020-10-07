package com.bigdata.tikitacar.review.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewUpdateRequestDto {
    private Long writer;
    private String title;
    private String content;
    private int rating;

    public void updateWriter(Long writer) {
        this.writer = writer;
    }
}
