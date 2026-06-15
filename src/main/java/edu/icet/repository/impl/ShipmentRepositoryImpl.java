package edu.icet.repository.impl;

import edu.icet.model.Shipment;
import edu.icet.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShipmentRepositoryImpl implements ShipmentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Shipment> getAll() {
        String sql = "SELECT * FROM shipments";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Shipment(
                rs.getLong("id"),
                rs.getString("shipment_number"),
                rs.getString("origin_city"),
                rs.getString("origin_address"),
                rs.getString("destination_city"),
                rs.getString("destination_address"),
                rs.getString("cargo_type"),
                rs.getDouble("cargo_weight_kg"),
                rs.getDouble("cargo_value"),
                rs.getString("status"),
                rs.getString("priority"),
                rs.getTimestamp("scheduled_pickup") != null ? rs.getTimestamp("scheduled_pickup").toLocalDateTime() : null,
                rs.getTimestamp("scheduled_delivery") != null ? rs.getTimestamp("scheduled_delivery").toLocalDateTime() : null,
                rs.getTimestamp("actual_pickup") != null ? rs.getTimestamp("actual_pickup").toLocalDateTime() : null,
                rs.getTimestamp("actual_delivery") != null ? rs.getTimestamp("actual_delivery").toLocalDateTime() : null,
                rs.getObject("vehicle_id") != null ? rs.getLong("vehicle_id") : null,
                rs.getObject("driver_id") != null ? rs.getLong("driver_id") : null,
                rs.getObject("created_by") != null ? rs.getLong("created_by") : null,
                rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
        ));
    }

    @Override
    public boolean add(Shipment shipment) {
        LocalDateTime createdAt = shipment.getCreatedAt() != null ? shipment.getCreatedAt() : LocalDateTime.now();
        LocalDateTime updatedAt = shipment.getUpdatedAt() != null ? shipment.getUpdatedAt() : LocalDateTime.now();

        String sql = "INSERT INTO shipments (shipment_number, origin_city, origin_address, destination_city, destination_address, cargo_type, cargo_weight_kg, cargo_value, status, priority, scheduled_pickup, scheduled_delivery, actual_pickup, actual_delivery, vehicle_id, driver_id, created_by, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                shipment.getShipmentNumber(),
                shipment.getOriginCity(),
                shipment.getOriginAddress(),
                shipment.getDestinationCity(),
                shipment.getDestinationAddress(),
                shipment.getCargoType(),
                shipment.getCargoWeightKg(),
                shipment.getCargoValue(),
                shipment.getStatus(),
                shipment.getPriority(),
                shipment.getScheduledPickup() != null ? Timestamp.valueOf(shipment.getScheduledPickup()) : null,
                shipment.getScheduledDelivery() != null ? Timestamp.valueOf(shipment.getScheduledDelivery()) : null,
                shipment.getActualPickup() != null ? Timestamp.valueOf(shipment.getActualPickup()) : null,
                shipment.getActualDelivery() != null ? Timestamp.valueOf(shipment.getActualDelivery()) : null,
                shipment.getVehicleId(),
                shipment.getDriverId(),
                shipment.getCreatedBy(),
                Timestamp.valueOf(createdAt),
                Timestamp.valueOf(updatedAt)
        ) > 0;
    }

    @Override
    public boolean update(Shipment shipment) {
        LocalDateTime updatedAt = LocalDateTime.now();
        String sql = "UPDATE shipments SET shipment_number=?, origin_city=?, origin_address=?, destination_city=?, destination_address=?, cargo_type=?, cargo_weight_kg=?, cargo_value=?, status=?, priority=?, scheduled_pickup=?, scheduled_delivery=?, actual_pickup=?, actual_delivery=?, vehicle_id=?, driver_id=?, created_by=?, updated_at=? WHERE id=?";
        return jdbcTemplate.update(sql,
                shipment.getShipmentNumber(),
                shipment.getOriginCity(),
                shipment.getOriginAddress(),
                shipment.getDestinationCity(),
                shipment.getDestinationAddress(),
                shipment.getCargoType(),
                shipment.getCargoWeightKg(),
                shipment.getCargoValue(),
                shipment.getStatus(),
                shipment.getPriority(),
                shipment.getScheduledPickup() != null ? Timestamp.valueOf(shipment.getScheduledPickup()) : null,
                shipment.getScheduledDelivery() != null ? Timestamp.valueOf(shipment.getScheduledDelivery()) : null,
                shipment.getActualPickup() != null ? Timestamp.valueOf(shipment.getActualPickup()) : null,
                shipment.getActualDelivery() != null ? Timestamp.valueOf(shipment.getActualDelivery()) : null,
                shipment.getVehicleId(),
                shipment.getDriverId(),
                shipment.getCreatedBy(),
                Timestamp.valueOf(updatedAt),
                shipment.getId()
        ) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM shipments WHERE id=?", id) > 0;
    }

    @Override
    public Shipment searchById(Long id) {
        try {
            String sql = "SELECT * FROM shipments WHERE id=?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Shipment(
                rs.getLong("id"),
                rs.getString("shipment_number"),
                rs.getString("origin_city"),
                rs.getString("origin_address"),
                rs.getString("destination_city"),
                rs.getString("destination_address"),
                rs.getString("cargo_type"),
                rs.getDouble("cargo_weight_kg"),
                rs.getDouble("cargo_value"),
                rs.getString("status"),
                rs.getString("priority"),
                rs.getTimestamp("scheduled_pickup") != null ? rs.getTimestamp("scheduled_pickup").toLocalDateTime() : null,
                rs.getTimestamp("scheduled_delivery") != null ? rs.getTimestamp("scheduled_delivery").toLocalDateTime() : null,
                rs.getTimestamp("actual_pickup") != null ? rs.getTimestamp("actual_pickup").toLocalDateTime() : null,
                rs.getTimestamp("actual_delivery") != null ? rs.getTimestamp("actual_delivery").toLocalDateTime() : null,
                rs.getObject("vehicle_id") != null ? rs.getLong("vehicle_id") : null,
                rs.getObject("driver_id") != null ? rs.getLong("driver_id") : null,
                rs.getObject("created_by") != null ? rs.getLong("created_by") : null,
                rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
            ));
        } catch (Exception e) {
            return null;
        }
    }
}
