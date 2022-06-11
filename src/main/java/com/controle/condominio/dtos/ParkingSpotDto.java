package com.controle.condominio.dtos;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ParkingSpotDto implements Serializable {
    @NotBlank
    private final String parkingSpotNumber;
    @NotBlank
    @Size(max = 7)
    private final String licensePlateCar;
    @NotBlank
    private final String brandCar;
    @NotBlank
    private final String modelCar;
    @NotBlank
    private final String colorCar;
    @NotBlank
    private final String resposibleName;
    @NotBlank
    private final String apartment;
    @NotBlank
    private final String block;
}
