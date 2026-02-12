package han.com.kg.bordoMal.dto.response;

import java.time.LocalDateTime;

public class NotificationResponse {
    private Long id;
    private String type;
    private String message;
    private Long referenceId;
    private boolean read;
    private LocalDateTime createdAt;

    public NotificationResponse(Long id, String type, String message, Long referenceId, boolean read, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.referenceId = referenceId;
        this.read = read;
        this.createdAt = createdAt;
    }

    public NotificationResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
