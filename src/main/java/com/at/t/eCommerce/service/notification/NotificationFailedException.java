package com.at.t.eCommerce.service.notification;

public class NotificationFailedException extends RuntimeException {
    public NotificationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
