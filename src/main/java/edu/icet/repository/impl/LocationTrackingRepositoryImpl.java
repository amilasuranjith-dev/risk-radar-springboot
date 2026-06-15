package edu.icet.repository.impl;

import edu.icet.model.LocationTracking;
import edu.icet.repository.LocationTrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LocationTrackingRepositoryImpl implements LocationTrackingRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(LocationTracking location) {
        LocalDateTime recordedAt = location.getRecordedAt() != null ? location.getRecordedAt() : LocalDateTime.now();
        String sql = "INSERT INTO location_tracking (shipment_id, vehicle_id, latitude, longitude, speed_kmh, temperature_celsius, fuel_level_percent, recorded_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                location.getShipmentId(),
                location.getVehicleId(),
                location.getLatitude(),
                location.getLongitude(),
                location.getSpeedKmh(),
                location.getTemperatureCelsius(),
                location.getFuelLevelPercent(),
                Timestamp.valueOf(recordedAt)
        ) > 0;
    }

    @Override
    public List<LocationTracking> getByShipmentId(Long shipmentId) {
        String sql = "SELECT * FROM location_tracking WHERE shipment_id = ? ORDER BY recorded_at DESC";
        return jdbcTemplate.query(sql, new Object[]{shipmentId}, (rs, rowNum) -> new LocationTracking(
                rs.getLong("id"),
                rs.getLong("shipment_id"),
                rs.getLong("vehicle_id"),
                rs.getDouble("latitude"),
                rs.getDouble("longitude"),
                rs.getDouble("speed_kmh"),
                rs.getDouble("temperature_celsius"),
                rs.getDouble("fuel_level_percent"),
                rs.getTimestamp("recorded_at") != null ? rs.getTimestamp("recorded_at").toLocalDateTime() : null
        ));
    }
}
