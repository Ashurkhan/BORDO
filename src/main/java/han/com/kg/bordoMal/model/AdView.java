package han.com.kg.bordoMal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ad_views")
public class AdView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viewer_id")
    private User viewer; // nullable (гость)

}
