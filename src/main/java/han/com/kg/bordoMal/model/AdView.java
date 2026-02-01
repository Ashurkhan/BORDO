package han.com.kg.bordoMal.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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


    @Column(nullable = false, updatable = false)
    private LocalDateTime viewedAt = LocalDateTime.now();

    public AdView(Long id, Ad ad, User viewer, LocalDateTime viewedAt) {
        this.id = id;
        this.ad = ad;
        this.viewer = viewer;
        this.viewedAt = viewedAt;
    }

    public AdView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public User getViewer() {
        return viewer;
    }

    public void setViewer(User viewer) {
        this.viewer = viewer;
    }

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }
}
