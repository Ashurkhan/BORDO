package han.com.kg.bordoMal.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "favourites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private Ad ad;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
