package study.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import study.demo.domain.FoodCategory;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
