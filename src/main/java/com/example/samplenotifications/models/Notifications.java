package com.example.samplenotifications.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notifications {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int notificationID;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "planID")
    private int planID;
}
