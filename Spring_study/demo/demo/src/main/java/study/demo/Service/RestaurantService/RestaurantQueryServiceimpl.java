package study.demo.Service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.demo.Repository.RegionRepository;
import study.demo.Repository.RestaurantRepository;
import study.demo.Repository.ReviewRepository;
import study.demo.Repository.UserRepository;
import study.demo.converter.RestaurantConverter;
import study.demo.converter.ReviewConverter;
import study.demo.domain.Restaurant;
import study.demo.domain.mapping.Review;
import study.demo.web.dto.RestaurantRequestDTO;
import study.demo.web.dto.ReviewRequestDTO;

//In service folder, Repository and Service that hanlded business logic and converter
@Service
@RequiredArgsConstructor
public class RestaurantQueryServiceimpl implements RestaurantQueryService {

    private final RestaurantRepository restaurantRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    //create the restaurant object and save logic , don't check validation in this code, just logic
    @Override
    public Restaurant Register(RestaurantRequestDTO.RegisterDTO request) {
        Restaurant newRestaurant = RestaurantConverter.toRestaurant(request);

        newRestaurant.SetRegion(regionRepository.findById(request.getRegion_id()).orElseThrow());
        restaurantRepository.save(newRestaurant);

        return newRestaurant;
    }

    @Override
    public Review addReview(ReviewRequestDTO.RegisterDTO request) {
        Review newReview = ReviewConverter.toReview(request);

        newReview.SetUser(userRepository.findById(request.getUser_id()).orElseThrow());
        newReview.SetRestaurant(restaurantRepository.findById(request.getRestaurant_id()).orElseThrow());

        reviewRepository.save(newReview);

        return newReview;
    }
}

