package han.com.kg.bordoMal.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal price;

    private String currency;

    @Enumerated(EnumType.STRING)
    private AdStatus status;

    @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AdView> views = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SubCategory subCategory;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location;


    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Favorite> favoritedBy = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Ad(Long id, String title, String description, BigDecimal price, String currency, AdStatus status, Set<AdView> views, User seller, Category category, SubCategory subCategory, Location location, Set<Favorite> favoritedBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.status = status;
        this.views = views;
        this.seller = seller;
        this.category = category;
        this.subCategory = subCategory;
        this.location = location;
        this.favoritedBy = favoritedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Ad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public AdStatus getStatus() {
        return status;
    }

    public void setStatus(AdStatus status) {
        this.status = status;
    }

    public Set<AdView> getViews() {
        return views;
    }

    public void setViews(Set<AdView> views) {
        this.views = views;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Favorite> getFavoritedBy() {
        return favoritedBy;
    }

    public void setFavoritedBy(Set<Favorite> favoritedBy) {
        this.favoritedBy = favoritedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
