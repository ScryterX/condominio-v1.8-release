package com.controle.condominio.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "parking_spot_model")
public class ParkingSpotModel {
    private static final long serialVersionUID = 1L;

//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private /*long*/UUID id;
    @Column(nullable = false, unique = true, length = 10)
    private String parkingSpotNumber;
    @Column(nullable = false, unique = true, length = 7)
    private String licensePlateCar;
    @Column(nullable = false, length = 70)
    private String brandCar;
    @Column(nullable = false, length = 70)
    private String modelCar;
    @Column(nullable = false, length = 70)
    private String colorCar;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @Column(nullable = false, length = 170)
    private String resposibleName;
    @Column(nullable = false, length = 30)
    private String apartment;
    @Column(nullable = false, length = 30)
    private String block;
}