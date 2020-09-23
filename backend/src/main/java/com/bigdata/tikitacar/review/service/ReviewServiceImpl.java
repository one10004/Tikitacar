package com.bigdata.tikitacar.review.service;

import com.bigdata.tikitacar.car.dto.response.DealSearchResponseDto;
import com.bigdata.tikitacar.car.entity.Deal;
import com.bigdata.tikitacar.car.repository.DealRepository;
import com.bigdata.tikitacar.review.dto.request.ReviewRegisterRequestDto;
import com.bigdata.tikitacar.review.dto.response.ReviewSearchResponseDto;
import com.bigdata.tikitacar.review.entity.Review;
import com.bigdata.tikitacar.review.repository.ReviewRepository;
import com.bigdata.tikitacar.user.entity.User;
import com.bigdata.tikitacar.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotAuthorizedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    DealRepository dealRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void registerReview(ReviewRegisterRequestDto reviewRegisterRequestDto) {

        User writer = Optional.of(userRepository.findById(reviewRegisterRequestDto.getWriter())
                .orElseThrow(() -> new NoSuchElementException("작성자가 존재하지 않음"))).get();

        System.out.println(writer.getId());

        Deal deal = Optional.of(dealRepository.findById(reviewRegisterRequestDto.getDeal())
                .orElseThrow(() -> new NoSuchElementException("거래가 존재하지 않음"))).get();

        if(deal.getBuyer() == null) throw new NoSuchElementException("판매가 완료되지 않은 거래");

        if(writer.getId() != deal.getBuyer().getId()) throw new NoSuchElementException("리뷰 작성의 권한이 없음");

        Review review = Review.builder()
                .deal(deal)
                .writer(writer)
                .title(reviewRegisterRequestDto.getTitle())
                .content(reviewRegisterRequestDto.getContent())
                .date(LocalDateTime.now())
                .rating(reviewRegisterRequestDto.getRating())
                .build();

        try {
            reviewRepository.save(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ReviewSearchResponseDto> searchAllReview(Pageable pageable) {
        List<ReviewSearchResponseDto> reviewSearchResponseDtoList = new ArrayList<>();

        Page<Review> list = reviewRepository.findAll(pageable);

        for(Review review : list) {
            reviewSearchResponseDtoList.add(ReviewSearchResponseDto.builder()
                    .id(review.getId())
                    .email(review.getWriter().getEmail())
                    .nickname(review.getWriter().getNickname())
                    .deal(review.getDeal())
                    .title(review.getTitle())
                    .content(review.getContent())
                    .date(review.getDate())
                    .rating(review.getRating())
                    .build());

        }

        return reviewSearchResponseDtoList;
    }

    @Override
    public ReviewSearchResponseDto searchReview(Long id) {
        Review review = Optional.of(reviewRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("후기가 존재하지 않음."))).get();

        ReviewSearchResponseDto reviewSearchResponseDto = ReviewSearchResponseDto.builder()
                .id(review.getId())
                .email(review.getWriter().getEmail())
                .nickname(review.getWriter().getNickname())
                .deal(review.getDeal())
                .title(review.getTitle())
                .content(review.getContent())
                .date(review.getDate())
                .rating(review.getRating())
                .build();

        return reviewSearchResponseDto;

    }

    @Override
    public void updateReview(ReviewRegisterRequestDto reviewRegisterRequestDto) {
    }

    @Override
    public void removeReview(Long id) {
    }

}
