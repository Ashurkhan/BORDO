package han.com.kg.bordoMal.dto.response;

import han.com.kg.bordoMal.model.AdStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AdResponse {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private String currency;
    private AdStatus status;

    private CategoryResponse category;
    private LocationResponse location;

    private SellerResponse seller;

    private int viewsCount;
    private int favoritesCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AdResponse(Long id, String title, String description, BigDecimal price, String currency, AdStatus status, CategoryResponse category, LocationResponse location, SellerResponse seller, int viewsCount, int favoritesCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.status = status;
        this.category = category;
        this.location = location;
        this.seller = seller;
        this.viewsCount = viewsCount;
        this.favoritesCount = favoritesCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AdResponse() {

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

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

    public LocationResponse getLocation() {
        return location;
    }

    public void setLocation(LocationResponse location) {
        this.location = location;
    }

    public SellerResponse getSeller() {
        return seller;
    }

    public void setSeller(SellerResponse seller) {
        this.seller = seller;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(int viewsCount) {
        this.viewsCount = viewsCount;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
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


