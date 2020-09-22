package com.bigdata.tikitacar.review.service;

import com.bigdata.tikitacar.review.dto.request.ReviewRegisterRequestDto;
import com.bigdata.tikitacar.review.dto.response.ReviewSearchResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {

    //Create
    public void registerReview(ReviewRegisterRequestDto reviewRegisterRequestDto);

    //Read
    public ReviewSearchResponseDto searchReview(Long id);

    //Update
    public void updateReview(ReviewRegisterRequestDto reviewRegisterRequestDto);

    //Delete
    public void removeReview(Long id);

    //List
    List<ReviewSearchResponseDto> searchAllReview(Pageable pageable);

}
