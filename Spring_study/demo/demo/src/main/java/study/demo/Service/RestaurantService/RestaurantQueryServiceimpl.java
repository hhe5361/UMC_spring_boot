package study.demo.Service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    //in that case, i have to get repository but i think it is okay . because it is not new instance just get in bean.
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
    public Review addReview(ReviewRequestDTO.WriteDTO request) {
        Review newReview = ReviewConverter.toReview(request); //get review and convert in templete of review

        //save user and restaurant by using setter
        //have to find user entity and restaurant id should i throw by elsehtrow? cause
        newReview.SetUser(userRepository.findById(request.getUser_id()).orElseThrow());
        newReview.SetRestaurant(restaurantRepository.findById(request.getRestaurant_id()).orElseThrow());

        //save newreview logic
        reviewRepository.save(newReview);

        return newReview;
    }

    @Override
    public Page<Review> getReviewList(Long restaurantId, Integer page) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        Page<Review> getReviewPage = reviewRepository.findAllByRestaurant(restaurant, PageRequest.of(page, 10));

        return getReviewPage;
    }
}

