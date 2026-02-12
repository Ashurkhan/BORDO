package han.com.kg.bordoMal.mapper.impl;

import han.com.kg.bordoMal.dto.response.NotificationResponse;
import han.com.kg.bordoMal.mapper.NotificationMapper;
import han.com.kg.bordoMal.model.Notification;
import han.com.kg.bordoMal.service.NotificationService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationResponse tDto(Notification n) {
        NotificationResponse r=new NotificationResponse();
        r.setId(n.getId());
        r.setType(n.getType().name());
        r.setMessage(n.getMessage());
        r.setReferenceId(n.getReferenceId());
        r.setRead(n.isRead());
        r.setCreatedAt(n.getCreatedAt());
        return r;
    }

    @Override
    public List<NotificationResponse> tDtos(List<Notification> notifications) {
        List<NotificationResponse> responses=new ArrayList<>();
        for(Notification notification:notifications){
            responses.add(tDto(notification));
        }
        return responses;
    }
}
