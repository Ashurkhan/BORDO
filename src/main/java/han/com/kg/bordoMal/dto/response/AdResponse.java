package han.com.kg.bordoMal.dto.response;

import han.com.kg.bordoMal.model.AdStatus;
import han.com.kg.bordoMal.model.SubCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AdResponse {

    private Long id;
    private String title;
    private String description;

    private BigDecimal price;
    private String currency;

    private AdStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int viewsCount;
    private int favoritesCount;

    private SellerResponse seller;

    private CategoryResponse category;

    private SubCategoryResponse subCategory;

    private LocationResponse location;
    private List<ImageResponse> images;
    private long likesCount;
    private long dislikesCount;

    public AdResponse(Long id, String title, String description, BigDecimal price, String currency, AdStatus status, LocalDateTime createdAt, LocalDateTime updatedAt, int viewsCount, int favoritesCount, SellerResponse seller, CategoryResponse category, SubCategoryResponse subCategory, LocationResponse location, List<ImageResponse> images, long likesCount, long dislikesCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.viewsCount = viewsCount;
        this.favoritesCount = favoritesCount;
        this.seller = seller;
        this.category = category;
        this.subCategory = subCategory;
        this.location = location;
        this.images = images;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
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

    public List<ImageResponse> getImages() {
        return images;
    }

    public void setImages(List<ImageResponse> images) {
        this.images = images;
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

    public SellerResponse getSeller() {
        return seller;
    }

    public void setSeller(SellerResponse seller) {
        this.seller = seller;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

    public SubCategoryResponse getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryResponse subCategory) {
        this.subCategory = subCategory;
    }

    public LocationResponse getLocation() {
        return location;
    }

    public void setLocation(LocationResponse location) {
        this.location = location;
    }

    public long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    public long getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(long dislikesCount) {
        this.dislikesCount = dislikesCount;
    }
}




