package study.demo.converter;

import org.springframework.data.domain.Page;
import study.demo.domain.mapping.Review;
import study.demo.web.dto.ReviewRequestDTO;
import study.demo.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    public static Review toReview(ReviewRequestDTO.WriteDTO dto){
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

    public static ReviewResponseDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review){
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .ownerNickName(review.getUser().getName())
                .content(review.getContent())
                .createAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewList){
        List<ReviewResponseDTO.ReviewPreviewDTO> reViewResponseList = reviewList.stream().map(ReviewConverter::toReviewPreviewDTO).toList();

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(reViewResponseList)
                .listSize(reviewList.getNumberOfElements())
                .isFirstPage(reviewList.isFirst())
                .isLastPage(reviewList.isLast())
                .totalElements(reviewList.getTotalElements())
                .build();
    }
}
