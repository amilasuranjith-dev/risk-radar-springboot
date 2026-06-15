package edu.icet.service;

import edu.icet.model.Alert;
import edu.icet.model.LocationTracking;
import edu.icet.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiskEngineService {

    private final AlertRepository alertRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public void evaluateLocation(LocationTracking location) {
        // Simple rules for demonstration

        // Rule 1: Temperature breach for reefers (simulate threshold > -10C is bad)
        if (location.getTemperatureCelsius() != null && location.getTemperatureCelsius() > -10.0) {
            createAndBroadcastAlert(
                    location,
                    "TEMPERATURE_BREACH",
                    "CRITICAL",
                    "Temperature breached safe limits: " + location.getTemperatureCelsius() + "C"
            );
        }

        // Rule 2: Delay risk (simulate speed == 0 as stuck)
        if (location.getSpeedKmh() != null && location.getSpeedKmh() == 0.0) {
            createAndBroadcastAlert(
                    location,
                    "DELAY_RISK",
                    "MEDIUM",
                    "Vehicle is stationary. Potential delay."
            );
        }

        // Rule 3: Idle alert (simulate low fuel)
        if (location.getFuelLevelPercent() != null && location.getFuelLevelPercent() < 15.0) {
            createAndBroadcastAlert(
                    location,
                    "IDLE_ALERT",
                    "HIGH",
                    "Fuel level critically low: " + location.getFuelLevelPercent() + "%"
            );
        }
    }

    private void createAndBroadcastAlert(LocationTracking location, String type, String severity, String message) {
        Alert alert = new Alert(
                null,
                location.getShipmentId(),
                location.getVehicleId(),
                type,
                severity,
                message,
                false,
                null,
                LocalDateTime.now()
        );

        // Save to database
        alertRepository.save(alert);
        
        // Broadcast over WebSocket
        messagingTemplate.convertAndSend("/topic/alerts", alert);
        log.info("RiskEngine generated alert: {}", message);
    }
}
