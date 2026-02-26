package edu.icet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vehicle {
    private Long id;
    private String licensePlate;
    private String model;
    private String manufacturer;
    private Integer year;
    private String vehicleType;
    private Double capacityType;
    private String fuelType;
    private String status;
    private LocalDate lastMaintenanceDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
