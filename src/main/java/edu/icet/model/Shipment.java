package edu.icet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Shipment {
    private Long id;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
