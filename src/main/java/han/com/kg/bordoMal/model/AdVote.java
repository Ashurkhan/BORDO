package han.com.kg.bordoMal.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ad_votes",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "ad_id"})})
public class AdVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private Ad ad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdVoteType type;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public AdVote(Long id, User user, Ad ad, AdVoteType type, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.ad = ad;
        this.type = type;
        this.createdAt = createdAt;
    }

    public AdVote() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public AdVoteType getType() {
        return type;
    }

    public void setType(AdVoteType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
