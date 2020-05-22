package com.angularBootRef.springBootPortfolio.service;

import com.angularBootRef.springBootPortfolio.domain.Review;
import java.util.Optional;

public interface ReviewService {

	Optional<Review> findById(Long reviewId);
	Review save(Review review);
}
