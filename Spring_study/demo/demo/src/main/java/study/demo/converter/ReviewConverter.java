package study.demo.converter;

import study.demo.domain.mapping.Review;
import study.demo.web.dto.ReviewRequestDTO;
import study.demo.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {
    public static Review toReview(ReviewRequestDTO.RegisterDTO dto){
        return Review.builder()
                .content(dto.getContent())
                .rating(dto.getRating())
                .build();
        //set user is prvided in service logic
    }

    public static ReviewResponseDTO.RegisterDTO toRegisterResponseDto(Review review){
        return ReviewResponseDTO.RegisterDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
