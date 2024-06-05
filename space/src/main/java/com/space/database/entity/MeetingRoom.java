package com.space.database.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Data
@Entity
@Table(name = "MEETING_ROOM")
public class MeetingRoom {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MEETING_ROOM_NAME")
    private String floorName;

    @Column(name = "MEETING_ROOM_NUMBER")
    private String floorNumber;

    @Column(name = "MEETING_ROOM_PICTURE")
    private String floorPicture;

    @ManyToOne()
    private Floor floor;
}
