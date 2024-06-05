package com.space.database.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
@Data
@Entity
@Table(name = "BUILDING")
public class Building {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BUILDING_NAME")
    private String buildingName;

    @Column(name="BUILDING_PICTURE")
    private String buildingPicture;
}
