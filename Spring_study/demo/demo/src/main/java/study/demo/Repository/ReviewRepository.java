package study.demo.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.demo.domain.Restaurant;
import study.demo.domain.mapping.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    //review page object
    Page<Review> findAllByRestaurant(Restaurant restaurant, Pageable pageable);
}
