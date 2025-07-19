package com.at.t.eCommerce.service.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotificationFactory {

    private final Map<String, NotificationService> services;

    public NotificationService getService(String type) {
        return services.getOrDefault(
            type.toUpperCase() + "NotificationService", // e.g. EMAILNotificationService
            services.get("defaultNotificationService")  // fallback
        );
    }
}
