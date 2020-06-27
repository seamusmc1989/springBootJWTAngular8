package com.angularBootRef.springBootPortfolio.service;

import com.angularBootRef.springBootPortfolio.domain.Review;
import com.angularBootRef.springBootPortfolio.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;

    //TODO add is ifPresentElse Throw exception
    @Override
    public Optional<Review> findById(Long reviewId) {
        log.info("findbyid logger info for argument " + reviewId );
        log.info("ReviewServiceImpl.findById " + reviewId);
        Optional<Review> byId = this.reviewRepository.findById(reviewId);
        return byId;
    }

    @Override
    public Review save(Review review) {
        log.info("save for the reviewwww.  logger info for argument " + review.toString() );
        return this.reviewRepository.save(review);
    }


}
