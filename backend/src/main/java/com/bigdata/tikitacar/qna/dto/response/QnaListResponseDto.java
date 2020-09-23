package com.bigdata.tikitacar.qna.dto.response;

import com.bigdata.tikitacar.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QnaListResponseDto {
    String nickname;
    String content;
    String reply;
    LocalDateTime regTime;
}
