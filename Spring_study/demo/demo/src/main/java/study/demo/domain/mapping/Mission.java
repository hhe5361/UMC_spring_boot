package study.demo.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import study.demo.domain.Region;
import study.demo.domain.Restaurant;
import study.demo.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int goal;

    private int reward;

//    @OneToOne
//    @JoinColumn(name = "region_id")
//    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<UserMission> userMissionList = new ArrayList<>();

}