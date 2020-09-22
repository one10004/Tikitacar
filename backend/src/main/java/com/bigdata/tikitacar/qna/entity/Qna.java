package com.bigdata.tikitacar.qna.entity;

import com.bigdata.tikitacar.car.entity.Deal;
import com.bigdata.tikitacar.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "qna")
public class Qna {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Deal.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "deal")
    private Deal deal;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private User writer;

    @Column(name = "content")
    private String content;

    @Column(name = "reply")
    private String reply;
}
