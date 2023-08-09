package com.example.samplenotifications.Controller;


import com.example.samplenotifications.Service.NotificationService;
import com.example.samplenotifications.Service.SeenNotificationService;
import com.example.samplenotifications.models.Notifications;
import com.example.samplenotifications.models.SeenNotifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("api/v1")
//@NoArgsConstructor
public class NotificationsController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SeenNotificationService seenNotificationService;

//    @Autowired
//    public NotificationsController(NotificationService notificationService,SeenNotificationService seenNotificationService){
////        this.notificationService = notificationService;
////        this.seenNotificationService = seenNotificationService;
//    }

    @PostMapping(path="/save")
    public ResponseEntity<String> addNotification(@RequestBody Notifications notifications){
        notificationService.addNotification(notifications);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created Notification!");
    }

    @GetMapping(path = "/{userID}/unseen")
    public ResponseEntity<List<Notifications>> getAllUnseenNotificationsByUser(@PathVariable int userID){
        List<Notifications> allNotifications = notificationService.getAllNotifications();
        List<Integer> seenNotificationsByUser = seenNotificationService.getSeenAllNotificationsByUser(userID);

        List<Notifications> unseenNotifications = allNotifications.stream()
                .filter(notification -> !seenNotificationsByUser.contains(notification.getNotificationID()))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(unseenNotifications);
    }

    @PostMapping(path = "/mark_seen/{userID}/{id}")
    public ResponseEntity<String> markNotificationasReadByUser(@PathVariable int userID,@PathVariable int id){
        Optional<Notifications> optionalNotifications = notificationService.isPresent(id);

        if(optionalNotifications.isEmpty())
            return ResponseEntity.notFound().build();

        SeenNotifications seenNotifications = new SeenNotifications();
        seenNotifications.setUserID(userID);
        seenNotifications.setSeenID(id);

        seenNotificationService.save(seenNotifications);

        return ResponseEntity.status(HttpStatus.CREATED).body("Notification marked as read for user with ID: " + userID);
    }

    @PostMapping(path = "/mark_all_seen/{userID}")
    public ResponseEntity<String> markAllNotificationsReadByUser(@PathVariable int userID){
        List<Notifications> allNotifications = notificationService.getAllNotifications();
        List<Integer> seenNotificationsByUser = seenNotificationService.getSeenAllNotificationsByUser(userID);

        List<Notifications> unseenNotifications = allNotifications.stream()
                .filter(notification -> !seenNotificationsByUser.contains(notification.getNotificationID()))
                .collect(Collectors.toList());

        for(Notifications notification : unseenNotifications){
            SeenNotifications seenNotifications = new SeenNotifications();
            seenNotifications.setUserID(userID);
            seenNotifications.setSeenID(notification.getNotificationID());
            seenNotificationService.save(seenNotifications);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("All notification marked as read for user with ID: " + userID);
    }

    @GetMapping(path = "/get_unread_count/{userID}")
    public ResponseEntity<Integer> getUnreadNotificationCount(@PathVariable int userID){
        List<Notifications> allNotifications = notificationService.getAllNotifications();
        List<Integer> seenNotificationsByUser = seenNotificationService.getSeenAllNotificationsByUser(userID);

        List<Notifications> unseenNotifications = allNotifications.stream()
                .filter(notification -> !seenNotificationsByUser.contains(notification.getNotificationID()))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(unseenNotifications.size());
    }
}
