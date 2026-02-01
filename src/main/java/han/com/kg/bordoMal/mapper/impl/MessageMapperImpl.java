package han.com.kg.bordoMal.mapper.impl;

import han.com.kg.bordoMal.dto.response.MessageResponse;
import han.com.kg.bordoMal.mapper.MessageMapper;
import han.com.kg.bordoMal.model.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapperImpl implements MessageMapper {
    @Override
    public MessageResponse tDto(Message msg) {
        MessageResponse resp = new MessageResponse();
        resp.setId(msg.getId());
        resp.setSenderId(msg.getSender().getId());
        resp.setSenderName(msg.getSender().getFullName());
        resp.setContent(msg.getContent());
        resp.setSentAt(msg.getSentAt());
        resp.setRead(msg.getRead());
        return resp;
    }

}
