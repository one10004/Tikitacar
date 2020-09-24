package com.bigdata.tikitacar.qna.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QnaQuestionUpdateRequestDto {
    String content;
}
