package com.at.t.eCommerce.service.notification.impl;

import com.at.t.eCommerce.model.NotificationModel;
import com.at.t.eCommerce.service.notification.NotificationService;
import com.at.t.eCommerce.service.notification.NotificationFailedException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service("EMAILNotificationService")// ✅ THIS is required for Spring to detect this bean
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationService {

    private final SesClient sesClient;

    @Override
    public void send(NotificationModel notification) {
        try {
            SendEmailRequest request = SendEmailRequest.builder()
                .destination(Destination.builder()
                    .toAddresses(notification.getUser().getEmail())
                    .build())
                .message(Message.builder()
                    .subject(Content.builder()
                        .data("Test SES Email ✅").build())
                    .body(Body.builder()
                        .text(Content.builder()
                            .data(notification.getMessage()).build())
                        .build())
                    .build())
                .source("pcmeanscomputer@gmail.com") // ✅ must be verified in SES
                .build();

            sesClient.sendEmail(request);
        } catch (SesException ex) {
        	System.err.println("❌ SES Error: " + ex.awsErrorDetails().errorMessage());
            throw new NotificationFailedException("SES send failed", ex);        }
    }
}
