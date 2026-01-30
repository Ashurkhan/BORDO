package han.com.kg.bordoMal.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class AdRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    private String currency;

    @NotNull
    private Long categoryID;


    @NotNull
    @Valid
    private LocationRequest location;

    public AdRequest(String title, String description, BigDecimal price, String currency, Long categoryID, LocationRequest location) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.categoryID = categoryID;
        this.location = location;
    }

    public AdRequest() {
    }

    public @NotBlank String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public @NotNull Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(@NotNull Long categoryID) {
        this.categoryID = categoryID;
    }

    public @NotNull @Valid LocationRequest getLocation() {
        return location;
    }

    public void setLocation(@NotNull @Valid LocationRequest location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull @Positive BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull @Positive BigDecimal price) {
        this.price = price;
    }

    public @NotBlank String getCurrency() {
        return currency;
    }

    public void setCurrency(@NotBlank String currency) {
        this.currency = currency;
    }

}

