package org.upgrad.services;

import java.util.List;

import org.upgrad.models.Notification;

public interface NotificationService {

    List<Notification> getNewNotifications(Long userId);
    List<Notification> getAllNotifications(Long userId);
    void createNotification(long userId, String message);
}
