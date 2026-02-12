package han.com.kg.bordoMal.service.impl;

import han.com.kg.bordoMal.exception.NotFoundException;
import han.com.kg.bordoMal.mapper.ChatMapper;
import han.com.kg.bordoMal.mapper.MessageMapper;
import han.com.kg.bordoMal.model.*;
import han.com.kg.bordoMal.repository.AdRepository;
import han.com.kg.bordoMal.repository.ChatRepository;
import han.com.kg.bordoMal.repository.MessageRepository;
import han.com.kg.bordoMal.service.ChatService;
import han.com.kg.bordoMal.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ChatServiceImpl implements ChatService {
    private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final AdRepository adRepository;
    private final NotificationService notificationService;

    public ChatServiceImpl(ChatRepository chatRepository, MessageRepository messageRepository, AdRepository adRepository, NotificationService notificationService) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.adRepository = adRepository;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public Chat createOrGetChat(Long adId, User buyer) {
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new NotFoundException("Ad не найден"));
        return chatRepository.findByAdAndBuyerAndSeller(ad, buyer, ad.getSeller())
                .orElseGet(() -> {
                    Chat chat = new Chat();
                    chat.setAd(ad);
                    chat.setBuyer(buyer);
                    chat.setSeller(ad.getSeller());
                    chat.setCreatedAt(LocalDateTime.now());
                    chat.setUpdatedAt(LocalDateTime.now());
                    return chatRepository.save(chat);
                });
    }

    @Override
    @Transactional
    public Message sendMessage(Long chatId, User sender, String content) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new NotFoundException("Чат не найден"));

        Message msg = new Message();
        msg.setChat(chat);
        msg.setSender(sender);
        msg.setContent(content);
        msg.setSentAt(LocalDateTime.now());
        msg.setRead(false);
        msg.setDeleted(false);

        chat.setUpdatedAt(LocalDateTime.now());
        chatRepository.save(chat);

        Message saved = messageRepository.save(msg);

        User receiver;
        if (sender.equals(chat.getSeller())) {
            receiver = chat.getBuyer();
        } else {
            receiver = chat.getSeller();
        }

        if (receiver != null) {
            try {
                notificationService.notify(
                        receiver,
                        NotificationType.NEW_MESSAGE,
                        "Новое сообщение по объявлению",
                        chat.getId()
                );
            } catch (Exception e) {
                log.error("Failed to send notification for chat {}: {}", chat.getId(), e.getMessage(), e);
            }
        }

        return saved;
    }

    @Override
    @Transactional
    public void markMessagesAsRead(Long chatId, User user) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new NotFoundException("Чат не найден"));

        chat.getMessages().stream()
                .filter(m -> !m.getSender().equals(user) && !m.getRead())
                .forEach(m -> m.setRead(true));
    }

    @Override
    @Transactional
    public void deleteMessage(Long messageId, User user) {
        Message msg = messageRepository.findById(messageId)
                .orElseThrow(() -> new NotFoundException("Сообщение не найдено"));

        if (!msg.getSender().equals(user)) {
            throw new RuntimeException("Нельзя удалять чужие сообщения");
        }

        msg.setDeleted(true);
        messageRepository.save(msg);
    }

    @Override
    public Page<Message> getMessages(Long chatId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("sentAt").ascending());
        return messageRepository.findByChatIdAndDeletedFalse(chatId, pageable);
    }
}
