package com.bigdata.tikitacar.review.service;

import com.bigdata.tikitacar.review.dto.request.ReviewRegisterRequestDto;
import com.bigdata.tikitacar.review.dto.request.ReviewUpdateRequestDto;
import com.bigdata.tikitacar.review.dto.response.ReviewSearchResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ReviewService {

    //Create
    public void registerReview(ReviewRegisterRequestDto reviewRegisterRequestDto);

    //List
    Map<String,Object> searchAllReview(Pageable pageable);

    //Read
    public ReviewSearchResponseDto searchReview(Long id);

    //Update
    public void updateReview(ReviewUpdateRequestDto reviewUpdateRequestDto, Long id);

    //Delete
    public void removeReview(Long id);

}
