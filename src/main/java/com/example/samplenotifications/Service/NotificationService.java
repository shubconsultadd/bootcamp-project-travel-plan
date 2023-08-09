package com.example.samplenotifications.Service;

import com.example.samplenotifications.Repo.NotificationsRepo;
import com.example.samplenotifications.models.Notifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationsRepo notificationsRepo;

    public void addNotification(Notifications notifications){
        notificationsRepo.save(notifications);
    }

    public List<Notifications> getAllNotifications() {
        return notificationsRepo.findAll();
    }

    public Optional<Notifications> isPresent(int userID){
        Optional<Notifications> optionalNotifications = notificationsRepo.findById(userID);
        return optionalNotifications;
    }
}
