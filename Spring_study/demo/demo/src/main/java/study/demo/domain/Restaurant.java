package study.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import study.demo.domain.common.BaseEntity;
import study.demo.domain.mapping.Mission;
import study.demo.domain.mapping.Review;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String address;
    @Column(nullable = true, length = 20)
    private String latitude;
    @Column(nullable = true, length = 20)
    private String longitude;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    public void SetRegion(Region region) {
        this.region = region;
    }
}