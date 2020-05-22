package com.angularBootRef.springBootPortfolio.controller;

import com.angularBootRef.springBootPortfolio.converter.ReviewDtoConverter;
import com.angularBootRef.springBootPortfolio.domain.Review;
import com.angularBootRef.springBootPortfolio.dto.ReviewDto;
import com.angularBootRef.springBootPortfolio.service.CarService;
import com.angularBootRef.springBootPortfolio.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/review")
@RestController
@AllArgsConstructor
public class ReviewController {

	private ReviewService reviewService;

	private CarService carService;

	private ReviewDtoConverter reviewDtoConverter;

	@GetMapping("/findById/{reviewId}")
	public ReviewDto findReviewById(@PathVariable Long reviewId) {

		Optional<Review> byId = this.reviewService.findById(reviewId);

		log.info("byId founddddd. logger info for argument " + byId.get().toString() );

		return this.reviewDtoConverter.convertToDto(byId.get());
	}

//	@PostMapping("/add/{carId}/review")
	@PostMapping("/add/{carId}/review")
	public Review createReview(@PathVariable (value = "carId") Long carId,
							 @Valid @RequestBody Review review) {

	return carService.findById(carId).map(car -> {
			review.setCar(car);
			return reviewService.save(review);
		}).orElseThrow(() -> new ResourceNotFoundException("CarId " + carId + " not found"));
	}

}
