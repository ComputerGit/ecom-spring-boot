package com.at.t.eCommerce.service.notification.impl;

import org.springframework.stereotype.Service;

import com.at.t.eCommerce.model.NotificationModel;
import com.at.t.eCommerce.service.notification.NotificationService;

@Service("defaultNotificationService")
public class DefaultNotificationService implements NotificationService {
    @Override
    public void send(NotificationModel notification) {
        System.err.println("No valid handler for: " + notification.getNotificationType());
    }
}

