package han.com.kg.bordoMal.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LocationRequest {
    @NotBlank
    private String street;
    @NotBlank
    private String village;
    @NotBlank
    private String city;
    @NotBlank
    private String region;
    @NotBlank
    private String country;

    public LocationRequest(String street, String village, String city, String region, String country) {
        this.street = street;
        this.village = village;
        this.city = city;
        this.region = region;
        this.country = country;
    }

    public LocationRequest() {
    }

    public @NotBlank String getStreet() {
        return street;
    }

    public void setStreet(@NotBlank String street) {
        this.street = street;
    }

    public @NotBlank String getVillage() {
        return village;
    }

    public void setVillage(@NotBlank String village) {
        this.village = village;
    }

    public @NotBlank String getCity() {
        return city;
    }

    public void setCity(@NotBlank String city) {
        this.city = city;
    }

    public @NotBlank String getRegion() {
        return region;
    }

    public void setRegion(@NotBlank String region) {
        this.region = region;
    }

    public @NotBlank String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank String country) {
        this.country = country;
    }
}
