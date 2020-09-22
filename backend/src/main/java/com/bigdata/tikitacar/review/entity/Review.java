package com.bigdata.tikitacar.review.entity;

import com.bigdata.tikitacar.car.entity.Deal;
import com.bigdata.tikitacar.user.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="review")
public class Review {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="deal")
    private Deal deal;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="writer")
    private User writer;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @Column(name="date")
    private LocalDateTime date;

    @Column(name="rating")
    private int rating;

    public void updateReview(String title,String content, int rating){
        this.title=title;
        this.content=content;
        this.rating=rating;
    }
}