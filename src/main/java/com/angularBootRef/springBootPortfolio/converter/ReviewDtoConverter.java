package com.angularBootRef.springBootPortfolio.converter;

import com.angularBootRef.springBootPortfolio.domain.Owner;
import com.angularBootRef.springBootPortfolio.domain.Review;
import com.angularBootRef.springBootPortfolio.dto.ReviewDto;
import com.angularBootRef.springBootPortfolio.dto.ReviewDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ReviewDtoConverter implements IObjectDtoConverter<ReviewDto, Review> {

    @Override
    public ReviewDto convertToDto(Review src) {
        final ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(src.getId());
        reviewDto.setComment(src.getComment());
        reviewDto.setRating(src.getRating());

        return reviewDto;
    }

    @Override
    public Review convertFromDto(ReviewDto src) {
        final Review review = new Review();
        review.setId(src.getId());
        review.setComment(src.getComment());
        review.setRating(src.getRating());

        return review;
    }
}
