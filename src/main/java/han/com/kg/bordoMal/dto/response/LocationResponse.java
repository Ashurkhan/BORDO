package han.com.kg.bordoMal.dto.response;

import org.springframework.scheduling.support.SimpleTriggerContext;

public class LocationResponse {
    private Long id;
    private String street;
    private String village;
    private String city;
    private String region;
    private String country;

    public LocationResponse(Long id, String street, String village, String city, String region, String country) {
        this.id = id;
        this.street = street;
        this.village = village;
        this.city = city;
        this.region = region;
        this.country = country;
    }

    public LocationResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
