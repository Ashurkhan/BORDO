package han.com.kg.bordoMal.dto;


public class UserCredentialsDto {
    private String email;
    private String password;

    public UserCredentialsDto(String password, String email) {
        this.password = password;
        this.email = email;
    }
    public UserCredentialsDto(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}