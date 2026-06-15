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
public class LocationTracking {
    private Long id;
    private Long shipmentId;
    private Long vehicleId;
    private Double latitude;
    private Double longitude;
    private Double speedKmh;
    private Double temperatureCelsius;
    private Double fuelLevelPercent;
    private LocalDateTime recordedAt;
}
