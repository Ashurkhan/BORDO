package han.com.kg.bordoMal.mapper;

import han.com.kg.bordoMal.dto.response.MessageResponse;
import han.com.kg.bordoMal.model.Message;

public interface MessageMapper {
    MessageResponse tDto(Message message);
}
