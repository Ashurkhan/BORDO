package han.com.kg.bordoMal.service.impl;


import han.com.kg.bordoMal.dto.response.NotificationResponse;
import han.com.kg.bordoMal.exception.NotFoundException;
import han.com.kg.bordoMal.mapper.NotificationMapper;
import han.com.kg.bordoMal.model.Notification;
import han.com.kg.bordoMal.model.NotificationType;
import han.com.kg.bordoMal.model.User;
import han.com.kg.bordoMal.repository.NotificationRepository;
import han.com.kg.bordoMal.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper mapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper mapper) {
        this.notificationRepository = notificationRepository;
        this.mapper = mapper;
    }

    @Override
    public void notify(User user, NotificationType type, String message, Long referenceId) {
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setType(type);
        notification.setMessage(message);
        notification.setReferenceId(referenceId);

        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationResponse> getMyNotifications(User user) {
        List<Notification> notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(user.getId());
        return mapper.tDtos(notifications);
    }

    @Override
    public void markAsRead(Long notificationId, User user) {
        Notification n = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotFoundException("Notification not found"));

        if (!n.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Access denied");

        n.setRead(true);
        notificationRepository.save(n);
    }
}
