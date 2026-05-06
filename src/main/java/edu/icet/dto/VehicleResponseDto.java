package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDto {
    private Long id;
    private String licensePlate;
    private String model;
    private String manufacturer;
    private Integer year;
    private String vehicleType;
    private Double capacityKg;
    private String fuelType;
    private String status;
    private LocalDate lastMaintenanceDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


