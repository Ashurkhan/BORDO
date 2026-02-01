package han.com.kg.bordoMal.dto.response;

import java.time.LocalDateTime;

public class MessageResponse {
    private Long id;
    private Long senderId;
    private String senderName;
    private String content;
    private LocalDateTime sentAt;
    private Boolean read;
    private Boolean deleted;

    public MessageResponse(Long id, Long senderId, String senderName, String content, LocalDateTime sentAt, Boolean read, Boolean deleted) {
        this.id = id;
        this.senderId = senderId;
        this.senderName = senderName;
        this.content = content;
        this.sentAt = sentAt;
        this.read = read;
        this.deleted = deleted;
    }
    public MessageResponse(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}