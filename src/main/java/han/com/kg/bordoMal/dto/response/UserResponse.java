package han.com.kg.bordoMal.dto.response;

import han.com.kg.bordoMal.model.Favorite;
import han.com.kg.bordoMal.model.UserRole;
import han.com.kg.bordoMal.model.UserStatus;

import java.time.LocalDateTime;
import java.util.Set;

public class UserResponse {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String passwordHash;
    private UserStatus status;
    private UserRole role;
    private Set<Favorite> favorites;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponse(Long id, String fullName, String phone, String email, String passwordHash, UserStatus status, UserRole role, Set<Favorite> favorites, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
        this.role = role;
        this.favorites = favorites;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserResponse() {

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Set<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
