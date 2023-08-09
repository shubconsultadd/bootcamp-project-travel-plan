package com.example.samplenotifications.Repo;

import com.example.samplenotifications.models.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsRepo extends JpaRepository<Notifications,Integer> {
//    @Query("Select n.id, n.description, n.planid FROM Notifications as n")
//    List<Notifications> getAllNotifications();
}
