package study.demo.Service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.demo.Repository.RegionRepository;
import study.demo.Repository.RestaurantRepository;
import study.demo.converter.RestaurantConverter;
import study.demo.domain.Restaurant;
import study.demo.web.dto.RestaurantRequestDTO;

//In service folder, Repository and Service that hanlded business logic and converter
@Service
@RequiredArgsConstructor
public class RestaurantQueryServiceimpl implements RestaurantQueryService {

    private final RestaurantRepository restaurantRepository;
    private final RegionRepository regionRepository;

    //create the restaurant object and save logic , don't check validation in this code, just logic
    @Override
    public Restaurant Register(RestaurantRequestDTO.RegisterDTO request) {
        Restaurant newRestaurant = RestaurantConverter.toRestaurant(request);
        newRestaurant.SetRegion(regionRepository.findById(request.getRegion_id()).orElseThrow());
        restaurantRepository.save(newRestaurant);
        return newRestaurant;
    }
}

