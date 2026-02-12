package han.com.kg.bordoMal.controller;

import han.com.kg.bordoMal.dto.response.NotificationResponse;
import han.com.kg.bordoMal.security.CustomUserDetails;
import han.com.kg.bordoMal.service.NotificationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

     private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }


    @GetMapping
    public List<NotificationResponse> myNotifications(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return service.getMyNotifications(userDetails.user());
    }


    @PatchMapping("/{id}/read")
    public void markAsRead(
            @PathVariable Long id,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        service.markAsRead(id, userDetails.user());
    }
}

