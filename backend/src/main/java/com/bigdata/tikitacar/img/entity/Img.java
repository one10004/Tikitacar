package com.bigdata.tikitacar.img.entity;
import com.bigdata.tikitacar.car.entity.Deal;
import com.bigdata.tikitacar.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Img {

    @Id  @GeneratedValue
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="deal")
    private Deal deal;

    private String src;
}
