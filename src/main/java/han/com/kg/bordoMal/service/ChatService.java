package han.com.kg.bordoMal.service;

import han.com.kg.bordoMal.model.Chat;
import han.com.kg.bordoMal.model.Message;
import han.com.kg.bordoMal.model.User;
import org.springframework.data.domain.Page;

public interface ChatService {
    Chat createOrGetChat(Long adId, User buyer);
    Message sendMessage(Long chatId, User sender, String content);
    void markMessagesAsRead(Long chatId, User user);
    void deleteMessage(Long messageId, User user);
    Page<Message> getMessages(Long chatId, int page, int size);
}
