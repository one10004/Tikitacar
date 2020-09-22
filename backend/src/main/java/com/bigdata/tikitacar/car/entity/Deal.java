package com.bigdata.tikitacar.car.entity;

import com.bigdata.tikitacar.user.entity.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="deal")
public class Deal {

    @Id @GeneratedValue
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="seller")
    private User seller;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="buyer")
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="car")
    private Car car;

    @Column(name="status")
    @ColumnDefault("판매중")
    private String status;

    @Column(name="date")
    @CreationTimestamp
    private LocalDateTime date;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    public void updateDeal(String title,String content){
        this.title=title;
        this.content=content;
    }


}
