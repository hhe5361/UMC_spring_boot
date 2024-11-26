package study.demo.domain;

import jakarta.persistence.*;
import lombok.*;
import study.demo.domain.common.BaseEntity;
import study.demo.domain.mapping.UserAgree;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 150)
    private String body;

    @Column(nullable = false, length = 50)
    private boolean optional;

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
    private List<UserAgree> userAgreeList = new ArrayList<>();

}