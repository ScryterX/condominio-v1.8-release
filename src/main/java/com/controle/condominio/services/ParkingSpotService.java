package com.controle.condominio.services;

import com.controle.condominio.models.ParkingSpotModel;
import com.controle.condominio.repositories.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    @Autowired
    ParkingSpotRepository parkingSpotRepository;

    @Transactional
    public ParkingSpotModel save (ParkingSpotModel parkingSpotModel){
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public List<ParkingSpotModel> findAll(){
        return parkingSpotRepository.findAll();
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }

    public Optional<ParkingSpotModel> findById(/*long*/UUID id) {
        return parkingSpotRepository.findById(id);
    }

    public void delete(ParkingSpotModel parkingSpotModel){
        parkingSpotRepository.delete(parkingSpotModel);
    }
}
