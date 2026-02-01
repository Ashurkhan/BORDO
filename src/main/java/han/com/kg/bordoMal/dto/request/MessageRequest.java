package han.com.kg.bordoMal.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MessageRequest {
    @NotNull
    private Long chatId;

    @NotBlank
    private String content;

    public MessageRequest(Long chatId, String content) {
        this.chatId = chatId;
        this.content = content;
    }

    public MessageRequest() {
    }

    public @NotNull Long getChatId() {
        return chatId;
    }

    public void setChatId(@NotNull Long chatId) {
        this.chatId = chatId;
    }

    public @NotBlank String getContent() {
        return content;
    }

    public void setContent(@NotBlank String content) {
        this.content = content;
    }
}
