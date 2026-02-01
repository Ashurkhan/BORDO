package han.com.kg.bordoMal.mapper;

import han.com.kg.bordoMal.dto.response.ChatResponse;
import han.com.kg.bordoMal.model.Chat;

public interface ChatMapper {
    ChatResponse tDto(Chat chat);

}
