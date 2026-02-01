package han.com.kg.bordoMal.dto.request;

import java.math.BigDecimal;

public class AdUpdateRequest {

    private String title;

    private String description;

    private BigDecimal price;

    private String currency;

    private Long categoryId;

    private SubCategoryRequest subCategory;

    private LocationRequest location;

    public AdUpdateRequest(String title, String description, BigDecimal price, String currency, Long categoryId, SubCategoryRequest subCategory, LocationRequest location) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.categoryId = categoryId;
        this.subCategory = subCategory;
        this.location = location;
    }

    public AdUpdateRequest(){}

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public SubCategoryRequest getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryRequest subCategory) {
        this.subCategory = subCategory;
    }

    public LocationRequest getLocation() {
        return location;
    }

    public void setLocation(LocationRequest location) {
        this.location = location;
    }
}

