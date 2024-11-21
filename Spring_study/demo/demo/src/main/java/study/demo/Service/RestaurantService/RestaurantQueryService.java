package study.demo.Service.RestaurantService;

import study.demo.domain.Restaurant;
import study.demo.web.dto.RestaurantRequestDTO;

public interface RestaurantQueryService {
    Restaurant Register(RestaurantRequestDTO.RegisterDTO request);
}
