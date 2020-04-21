package org.upgrad.services;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.upgrad.models.Notification;
import org.upgrad.repositories.NotificationRepository;

@Service
public class NotificationServiceImp implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImp(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public ArrayList<Notification> getNewNotifications(Long userId) {
        ArrayList<Notification> notifications = notificationRepository.getNewNotifications(userId);
        notificationRepository.markNewNotificationsAsRead();
        return notifications;
    }

    @Override
    public ArrayList<Notification> getAllNotifications(Long userId) {
        ArrayList<Notification> notifications = notificationRepository.getAllNotifications(userId);
        notificationRepository.markNewNotificationsAsRead();
        return notifications;
    }

	@Override
	public void createNotification(long userId, String message) {
		notificationRepository.createNotification(userId, message, new Date());
	}
}
