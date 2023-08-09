package com.example.samplenotifications.Service;

import com.example.samplenotifications.Repo.SeenNotificationsRepo;
import com.example.samplenotifications.models.SeenNotifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeenNotificationService {

    @Autowired
    private SeenNotificationsRepo seenNotificationsRepo;

    public List<Integer> getSeenAllNotificationsByUser(int userID){
//        return seenNotificationsRepo.getSeenAllNotificationsByUser(userID);
        List<SeenNotifications> seenNotifications = seenNotificationsRepo.findByUserID(userID);
        List<Integer> seenIds = new ArrayList<>();
        for(SeenNotifications seenNotification: seenNotifications){
            seenIds.add(seenNotification.getSeenID());
        }
        System.out.println(seenIds);
        return seenIds;
    }

    public void save(SeenNotifications seenNotifications){
        seenNotificationsRepo.save(seenNotifications);
    }
}
