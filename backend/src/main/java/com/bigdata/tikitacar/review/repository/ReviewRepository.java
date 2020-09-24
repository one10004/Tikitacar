package com.bigdata.tikitacar.review.repository;

import com.bigdata.tikitacar.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    //리스트 페이징
    Page<Review> findAll(Pageable pageable);
}