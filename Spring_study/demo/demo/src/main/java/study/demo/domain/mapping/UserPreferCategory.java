package study.demo.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import study.demo.domain.FoodCategory;
import study.demo.domain.User;
import study.demo.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserPreferCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private FoodCategory foodCategory;

    public void SetUser(User user) {
        if(this.user != null) {
            user.getUserPreferList().remove(this);
        }
        this.user = user;
        user.getUserPreferList().add(this);
    }

    public void SetFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }
}