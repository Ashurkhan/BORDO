package han.com.kg.bordoMal.controller;

import han.com.kg.bordoMal.dto.request.MessageRequest;
import han.com.kg.bordoMal.dto.response.ChatResponse;
import han.com.kg.bordoMal.dto.response.MessageResponse;
import han.com.kg.bordoMal.mapper.ChatMapper;
import han.com.kg.bordoMal.mapper.MessageMapper;
import han.com.kg.bordoMal.model.Chat;
import han.com.kg.bordoMal.model.Message;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.security.CustomUserDetails;
import han.com.kg.bordoMal.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/chats")
@RestController
public class ChatController {
    private final ChatService chatService;
    private  final MessageMapper messageMapper;
    private final ChatMapper chatMapper;

    public ChatController(ChatService chatService, MessageMapper messageMapper, ChatMapper chatMapper) {
        this.chatService = chatService;
        this.messageMapper = messageMapper;
        this.chatMapper = chatMapper;
    }

    @PostMapping("/open")
    public ResponseEntity<ChatResponse> openChat(@RequestParam Long adId,
                                                 @AuthenticationPrincipal CustomUserDetails userDetails) {
        User buyer = userDetails.user();
        Chat chat = chatService.createOrGetChat(adId, buyer);

        ChatResponse resp = chatMapper.tDto(chat);
        return ResponseEntity.ok(resp);
    }


    @PostMapping("/message")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody @Valid MessageRequest request,
                                                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        User sender = userDetails.user();
        Message msg = chatService.sendMessage(request.getChatId(), sender, request.getContent());

        return ResponseEntity.ok(messageMapper.tDto(msg));
    }

    @PostMapping("/{chatId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long chatId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.user();
        chatService.markMessagesAsRead(chatId, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/message/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId,
                                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.user();
        chatService.deleteMessage(messageId, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<Page<MessageResponse>> getMessages(@PathVariable Long chatId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "20") int size) {
        Page<Message> messages = chatService.getMessages(chatId, page, size);
        return ResponseEntity.ok(messages.map(messageMapper::tDto));
    }
}
