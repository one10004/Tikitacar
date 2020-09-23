package com.bigdata.tikitacar.car.entity;

import com.bigdata.tikitacar.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="waiting")
public class Waiting {

    @Id @GeneratedValue
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="deal")
    private Deal deal;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="buyer")
    private User buyer;

}
