package com.controle.condominio.controllers;

import com.controle.condominio.dtos.ParkingSpotDto;
import com.controle.condominio.models.ParkingSpotModel;
import com.controle.condominio.services.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    ParkingSpotService parkingSpotService;


    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto)
    {
        if(parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Placa veicular já registrada");
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Vaga de estacionamento ocupada");
        if(parkingSpotService.existByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Limite de vagas de estacionamento cheio");

        ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel)) ;
    }


    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots()
    {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") /*long*/UUID id)
    {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Oops! Vaga de estacionamento não encontrada");}
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deleteOneParkingSpot(@PathVariable(value = "id") /*long*/UUID id)
    {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Oops! Vaga de estacionamento não encontrada. Não foi possível deletar");}
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("vaga de estacionamento deletada");
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Object> deleteOneParkingSpot(@PathVariable(value = "id") /*long*/UUID id, @RequestBody @Valid ParkingSpotDto parkingSpotDto)
    {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if(!parkingSpotModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Oops! Vaga de estacionamento não encontrada");
        }

        ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }


}
