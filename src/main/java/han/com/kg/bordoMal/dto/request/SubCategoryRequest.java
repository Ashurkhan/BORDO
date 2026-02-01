package han.com.kg.bordoMal.dto.request;

public class SubCategoryRequest {

    private String name;


    public SubCategoryRequest(String name) {
        this.name = name;
    }

    public SubCategoryRequest(){}
    public  String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }


}
