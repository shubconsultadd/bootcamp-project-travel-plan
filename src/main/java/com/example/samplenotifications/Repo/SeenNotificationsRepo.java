package com.example.samplenotifications.Repo;

import com.example.samplenotifications.models.SeenNotifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeenNotificationsRepo extends JpaRepository<SeenNotifications,Integer> {
//    @Query("Select s.seen FROM seen_notifications s where s.userID = ?1")
//    List<Integer> getSeenAllNotificationsByUser(int userID);

    List<SeenNotifications> findByUserID(int userID);
}
