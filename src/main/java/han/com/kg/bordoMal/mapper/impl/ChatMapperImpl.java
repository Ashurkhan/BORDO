package han.com.kg.bordoMal.mapper.impl;

import han.com.kg.bordoMal.dto.response.ChatResponse;
import han.com.kg.bordoMal.mapper.ChatMapper;
import han.com.kg.bordoMal.mapper.MessageMapper;
import han.com.kg.bordoMal.model.Chat;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ChatMapperImpl implements ChatMapper {
    private final MessageMapper messageMapper;

    public ChatMapperImpl(MessageMapper messageMapper){
        this.messageMapper=messageMapper;
    }
    @Override
    public ChatResponse tDto(Chat chat) {
        ChatResponse resp = new ChatResponse();
        resp.setId(chat.getId());
        resp.setAdId(chat.getAd().getId());
        resp.setAdTitle(chat.getAd().getTitle());
        resp.setBuyerId(chat.getBuyer().getId());
        resp.setBuyerName(chat.getBuyer().getFullName());
        resp.setSellerId(chat.getSeller().getId());
        resp.setSellerName(chat.getSeller().getFullName());
        resp.setMessages(chat.getMessages().stream()
                .map(messageMapper::tDto)
                .collect(Collectors.toList()));
        return resp;
    }
}
