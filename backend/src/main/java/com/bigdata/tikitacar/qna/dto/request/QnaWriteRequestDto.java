package com.bigdata.tikitacar.qna.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QnaWriteRequestDto {
    Long deal;
    String content;
}
