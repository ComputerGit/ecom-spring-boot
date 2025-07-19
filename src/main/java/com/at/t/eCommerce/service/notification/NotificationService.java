package com.at.t.eCommerce.service.notification;

import com.at.t.eCommerce.model.NotificationModel;

public interface NotificationService {
    void send(NotificationModel notification);
}
