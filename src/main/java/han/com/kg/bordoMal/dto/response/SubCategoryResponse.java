package han.com.kg.bordoMal.dto.response;

public class SubCategoryResponse {
    private Long id;
    private String name;

    public SubCategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public SubCategoryResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

