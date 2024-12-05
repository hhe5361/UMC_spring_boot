package study.demo.Service.RestaurantService;

import org.springframework.data.domain.Page;
import study.demo.domain.Restaurant;
import study.demo.domain.mapping.Review;
import study.demo.web.dto.RestaurantRequestDTO;
import study.demo.web.dto.ReviewRequestDTO;

public interface RestaurantQueryService {
    Restaurant Register(RestaurantRequestDTO.RegisterDTO request);
    Review addReview (ReviewRequestDTO.WriteDTO request);
    Page<Review> getReviewList(Long restaurantId, Integer page);
}
