package com.controle.condominio.repositories;

import com.controle.condominio.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {

    Optional<ParkingSpotModel> findById(/*long*/UUID id);
    boolean existsByLicensePlateCar(String licensePlateCar);

    boolean existsByParkingSpotNumber(String ParkingSpotNumber);

    boolean existsByApartmentAndBlock(String apartment, String block);
}