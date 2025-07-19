package com.at.t.eCommerce.controllers;

import com.at.t.eCommerce.enums.NotificationStatus;
import com.at.t.eCommerce.enums.NotificationType;
import com.at.t.eCommerce.model.*;
import com.at.t.eCommerce.service.notification.NotificationDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class NotificationTestController {

    private final NotificationDispatcher notificationDispatcher;

    @GetMapping("/test-email")
    public String testSendEmail() {
        UserModel recipient = new UserModel();
        recipient.setId(2L); // optional
        recipient.setEmail("rangerbeats01@gmail.com");

        NotificationModel notification = new NotificationModel();
        notification.setUser(recipient);
        notification.setMessage("This is a test email from SES");
        notification.setNotificationType(NotificationType.EMAIL);
        notification.setNotificationStatus(NotificationStatus.UNREAD);

        notificationDispatcher.dispatch(notification);

        return "✅ Email sent to ranger@gmail.com";
    }
}
