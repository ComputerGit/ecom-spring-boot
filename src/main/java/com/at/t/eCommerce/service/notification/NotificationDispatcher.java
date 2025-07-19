package com.at.t.eCommerce.service.notification;

import com.at.t.eCommerce.model.NotificationModel;
import com.at.t.eCommerce.repo.NotificationModelRepo;
import com.at.t.eCommerce.enums.NotificationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationDispatcher {

    private final NotificationFactory factory;
    private final NotificationModelRepo repo;

    @Transactional
    public NotificationModel dispatch(NotificationModel notification) {
        // Save to DB
        NotificationModel saved = repo.save(notification);
        

        try {
            // Send via correct channel (e.g. email)
            factory.getService(saved.getNotificationType().name())
                   .send(saved);

            // ✅ Correct way to set status
            saved.setNotificationStatus(NotificationStatus.UNREAD);
        } catch (Exception ex) {
            System.err.println("❌ Notification send failed: " + ex.getMessage());

            // ✅ Optional: still mark as UNREAD or FAILED if you want
            saved.setNotificationStatus(NotificationStatus.UNREAD);
        }

        return repo.save(saved); // Save updated status
    }
}
