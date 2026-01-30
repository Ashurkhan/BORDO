package han.com.kg.bordoMal.dto.response;

public class SellerResponse {
    private Long id;
    private String fullName;

    public SellerResponse(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public SellerResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
