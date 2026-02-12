package han.com.kg.bordoMal.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // кому уведомление
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // тип уведомления
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    // текст (готовый для фронта)
    @Column(nullable = false)
    private String message;

    // ссылка (adId, chatId и т.п.)
    private Long referenceId;

    @Column(name = "is_read", nullable = false)
    private boolean read;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @PrePersist
    void onCreate() {
        this.read = false;
        this.createdAt = LocalDateTime.now();
    }

    public Notification(Long id, User user, NotificationType type, String message, Long referenceId, boolean read, LocalDateTime createdAt) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.message = message;
        this.referenceId = referenceId;
        this.read = read;
        this.createdAt = createdAt;
    }

    public Notification(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
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
