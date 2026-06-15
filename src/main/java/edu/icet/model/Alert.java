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
public class Alert {
    private Long id;
    private Long shipmentId;
    private Long vehicleId;
    private String alertType;
    private String severity;
    private String message;
    private Boolean isResolved;
    private LocalDateTime resolvedAt;
    private LocalDateTime createdAt;
}
