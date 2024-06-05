package com.space.database.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Data
@Entity
@Table(name = "FLOOR")
public class Floor {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FLOOR_NAME")
    private String floorName;

    @Column(name = "FLOOR_NUMBER")
    private String floorNumber;

    @Column(name = "FLOOR_PICTURE")
    private String floorPicture;

    @ManyToOne()
    private Building building;
}
