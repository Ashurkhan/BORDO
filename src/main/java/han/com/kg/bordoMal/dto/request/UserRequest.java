package han.com.kg.bordoMal.dto.request;

import jakarta.validation.constraints.*;

public class UserRequest {
    @NotBlank
    private String fullName;
    @NotNull
    private String phone;
    @Email
    @NotNull
    private String email;
    @NotNull
    @Size(min = 5,max = 8)
    private String passwordHash;

    public UserRequest(String fullName, String phone, String email, String passwordHash) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public UserRequest() {

    }

    public @NotBlank String getFullName() {
        return fullName;
    }

    public void setFullName(@NotBlank String fullName) {
        this.fullName = fullName;
    }

    public @NotNull String getPhone() {
        return phone;
    }

    public void setPhone(@NotNull String phone) {
        this.phone = phone;
    }

    public @Email @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotNull String email) {
        this.email = email;
    }

    public @NotNull @Size(min = 5, max = 8) String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(@NotNull @Size(min = 5, max = 8) String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
