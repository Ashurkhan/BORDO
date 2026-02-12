package han.com.kg.bordoMal.service;

import han.com.kg.bordoMal.dto.response.NotificationResponse;
import han.com.kg.bordoMal.model.Notification;
import han.com.kg.bordoMal.model.NotificationType;
import han.com.kg.bordoMal.model.User;

import java.util.List;

public interface NotificationService {
    void notify(User user, NotificationType type, String message, Long referenceId);
    List<NotificationResponse> getMyNotifications(User user);
    void markAsRead(Long notificationId, User user);
}
