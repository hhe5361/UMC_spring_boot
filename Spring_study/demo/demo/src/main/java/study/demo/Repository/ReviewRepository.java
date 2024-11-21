package study.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.demo.domain.mapping.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
