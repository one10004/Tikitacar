package com.bigdata.tikitacar.review.service;

import com.bigdata.tikitacar.car.entity.Deal;
import com.bigdata.tikitacar.car.repository.DealRepository;
import com.bigdata.tikitacar.review.dto.request.ReviewRegisterRequestDto;
import com.bigdata.tikitacar.review.dto.response.ReviewSearchResponseDto;
import com.bigdata.tikitacar.review.entity.Review;
import com.bigdata.tikitacar.review.repository.ReviewRepository;
import com.bigdata.tikitacar.user.entity.User;
import com.bigdata.tikitacar.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import javax.ws.rs.NotAuthorizedException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    DealRepository dealRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void registerReview(ReviewRegisterRequestDto reviewRegisterRequestDto) {

        User writer = Optional.of(userRepository.findById(reviewRegisterRequestDto.getWriter())
                .orElseThrow(() -> new NoSuchElementException("작성자가 존재하지 않음."))).get();

        Deal deal = Optional.of(dealRepository.findById(reviewRegisterRequestDto.getDeal())
                .orElseThrow(() -> new NoSuchElementException("거래가 존재하지 않음."))).get();

        if(writer.getId() != deal.getBuyer().getId()) new NotAuthorizedException("리뷰 작성의 권한이 없음.");

        Review review = Review.builder()
                .deal(deal)
                .writer(writer)
                .title(reviewRegisterRequestDto.getTitle())
                .content(reviewRegisterRequestDto.getContent())
                .rating(reviewRegisterRequestDto.getRating())
                .build();

        try {
            reviewRepository.save(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ReviewSearchResponseDto searchReview(Long id) {
        return null;
    }

    @Override
    public void updateReview(ReviewRegisterRequestDto reviewRegisterRequestDto) {

    }

    @Override
    public void removeReview(Long id) {

    }

    @Override
    public List<ReviewSearchResponseDto> searchAllReview(Pageable pageable) {
        return null;
    }
}
