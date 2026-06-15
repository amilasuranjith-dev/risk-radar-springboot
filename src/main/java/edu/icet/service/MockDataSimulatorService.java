package edu.icet.service;

import edu.icet.model.LocationTracking;
import edu.icet.model.Shipment;
import edu.icet.repository.LocationTrackingRepository;
import edu.icet.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MockDataSimulatorService {

    private final ShipmentRepository shipmentRepository;
    private final LocationTrackingRepository locationRepository;
    private final RiskEngineService riskEngineService;
    private final SimpMessagingTemplate messagingTemplate;

    private final Random random = new Random();
    
    // Store current simulated location for each active shipment
    private final Map<Long, LocationTracking> currentLocations = new HashMap<>();

    // Base coordinates (e.g., Colombo, Sri Lanka roughly 6.9271, 79.8612)
    private static final double BASE_LAT = 6.9271;
    private static final double BASE_LON = 79.8612;

    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void simulateLiveData() {
        // Find IN_TRANSIT shipments
        List<Shipment> activeShipments = shipmentRepository.getAll().stream()
                .filter(s -> "IN_TRANSIT".equalsIgnoreCase(s.getStatus()))
                .collect(Collectors.toList());

        for (Shipment shipment : activeShipments) {
            LocationTracking nextLocation = generateNextLocation(shipment);
            
            // 1. Save to DB
            locationRepository.save(nextLocation);
            
            // 2. Broadcast to WebSocket Map
            messagingTemplate.convertAndSend("/topic/locations", nextLocation);
            
            // 3. Evaluate Risks
            riskEngineService.evaluateLocation(nextLocation);
            
            // 4. Update memory cache
            currentLocations.put(shipment.getId(), nextLocation);
        }
        
        if (!activeShipments.isEmpty()) {
            log.debug("Simulated data for {} active shipments", activeShipments.size());
        }
    }

    private LocationTracking generateNextLocation(Shipment shipment) {
        LocationTracking lastLocation = currentLocations.getOrDefault(shipment.getId(), null);
        
        double lat, lon, speed, temp, fuel;

        if (lastLocation == null) {
            // Start near base
            lat = BASE_LAT + (random.nextDouble() - 0.5) * 0.1;
            lon = BASE_LON + (random.nextDouble() - 0.5) * 0.1;
            speed = 40.0 + random.nextDouble() * 20;
            temp = -15.0 + random.nextDouble() * 5; // Good reef temp
            fuel = 100.0;
        } else {
            // Move slightly
            lat = lastLocation.getLatitude() + (random.nextDouble() - 0.5) * 0.005;
            lon = lastLocation.getLongitude() + (random.nextDouble() - 0.5) * 0.005;
            
            // Randomly stop to trigger delay risk (5% chance)
            speed = random.nextDouble() > 0.95 ? 0.0 : 40.0 + random.nextDouble() * 20;
            
            // Randomly breach temp (5% chance)
            temp = random.nextDouble() > 0.95 ? -5.0 : -15.0 + random.nextDouble() * 5;
            
            // Slowly decrease fuel
            fuel = Math.max(0, lastLocation.getFuelLevelPercent() - 0.5);
        }

        return new LocationTracking(
                null,
                shipment.getId(),
                shipment.getVehicleId(),
                lat,
                lon,
                speed,
                temp,
                fuel,
                LocalDateTime.now()
        );
    }
}
