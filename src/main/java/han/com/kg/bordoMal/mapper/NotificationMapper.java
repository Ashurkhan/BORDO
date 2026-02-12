package han.com.kg.bordoMal.mapper;

import han.com.kg.bordoMal.dto.response.NotificationResponse;
import han.com.kg.bordoMal.model.Notification;

import java.util.List;

public interface NotificationMapper {
    NotificationResponse tDto(Notification notification);
    List<NotificationResponse> tDtos(List<Notification> no);
}
