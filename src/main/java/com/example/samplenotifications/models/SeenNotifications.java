package com.example.samplenotifications.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="SeenNotifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeenNotifications {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="userID")
    private int userID;

    @Column(name="seen")
    private int seenID;
}
