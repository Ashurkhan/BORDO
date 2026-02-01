package han.com.kg.bordoMal.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LocationRequest {
    private String street;
    private String village;
    private String city;
    private String region;
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

    public  String getStreet() {
        return street;
    }

    public void setStreet( String street) {
        this.street = street;
    }

    public  String getVillage() {
        return village;
    }

    public void setVillage( String village) {
        this.village = village;
    }

    public  String getCity() {
        return city;
    }

    public void setCity( String city) {
        this.city = city;
    }

    public  String getRegion() {
        return region;
    }

    public void setRegion( String region) {
        this.region = region;
    }

    public  String getCountry() {
        return country;
    }

    public void setCountry( String country) {
        this.country = country;
    }
}
