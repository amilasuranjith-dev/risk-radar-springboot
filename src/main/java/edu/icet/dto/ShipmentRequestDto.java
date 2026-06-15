package edu.icet.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRequestDto {
    private String shipmentNumber;
    private String originCity;
    private String originAddress;
    private String destinationCity;
    private String destinationAddress;
    private String cargoType;
    private Double cargoWeightKg;
    private Double cargoValue;
    private String status;
    private String priority;
    private LocalDateTime scheduledPickup;
    private LocalDateTime scheduledDelivery;
    private LocalDateTime actualPickup;
    private LocalDateTime actualDelivery;
    private Long vehicleId;
    private Long driverId;
    private Long createdBy;
}
