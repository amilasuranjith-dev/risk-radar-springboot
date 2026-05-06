package edu.icet.repository.impl;

import edu.icet.model.Vehicle;
import edu.icet.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VehicleRepositoryImpl implements VehicleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Vehicle> getAll() {
        String sql = "SELECT id, license_plate, model, manufacturer, year, vehicle_type, capacity_kg, fuel_type, status, last_maintenance_date, created_at, updated_at FROM vehicles";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new Vehicle(
                    rs.getLong("id"),
                    rs.getString("license_plate"),
                    rs.getString("model"),
                    rs.getString("manufacturer"),
                    rs.getInt("year"),
                    rs.getString("vehicle_type"),
                    rs.getDouble("capacity_kg"),
                    rs.getString("fuel_type"),
                    rs.getString("status"),
                    rs.getDate("last_maintenance_date").toLocalDate(),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getTimestamp("updated_at").toLocalDateTime()
            )
        );
    }

    @Override
    public boolean add(Vehicle vehicle) {
        LocalDateTime createdAt = vehicle.getCreatedAt() != null ? vehicle.getCreatedAt() : LocalDateTime.now();
        LocalDateTime updatedAt = vehicle.getUpdatedAt() != null ? vehicle.getUpdatedAt() : LocalDateTime.now();

        return jdbcTemplate.update("INSERT INTO vehicles (license_plate, model, manufacturer, year, vehicle_type, capacity_kg, fuel_type, status, last_maintenance_date, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                vehicle.getLicensePlate(),
                vehicle.getModel(),
                vehicle.getManufacturer(),
                vehicle.getYear(),
                vehicle.getVehicleType(),
                vehicle.getCapacityKg(),
                vehicle.getFuelType(),
                vehicle.getStatus(),
                vehicle.getLastMaintenanceDate(),
                Timestamp.valueOf(createdAt),
                Timestamp.valueOf(updatedAt)
                ) > 0;
    }

    @Override
    public boolean update(Vehicle vehicle) {
        LocalDateTime updatedAt = LocalDateTime.now();

        return jdbcTemplate.update("UPDATE vehicles SET license_plate=?, model=?, manufacturer=?, year=?, vehicle_type=?, capacity_kg=?, fuel_type=?, status=?, last_maintenance_date=?, updated_at=? WHERE id=?",
                vehicle.getLicensePlate(),
                vehicle.getModel(),
                vehicle.getManufacturer(),
                vehicle.getYear(),
                vehicle.getVehicleType(),
                vehicle.getCapacityKg(),
                vehicle.getFuelType(),
                vehicle.getStatus(),
                vehicle.getLastMaintenanceDate(),
                Timestamp.valueOf(updatedAt),
                vehicle.getId()
                ) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM vehicles WHERE id=?", id) > 0;
    }

    @Override
    public Vehicle searchById(Long id) {
        try {
            String sql = "SELECT id, license_plate, model, manufacturer, year, vehicle_type, capacity_kg, fuel_type, status, last_maintenance_date, created_at, updated_at FROM vehicles WHERE id=?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Vehicle(
                        rs.getLong("id"),
                        rs.getString("license_plate"),
                        rs.getString("model"),
                        rs.getString("manufacturer"),
                        rs.getInt("year"),
                        rs.getString("vehicle_type"),
                        rs.getDouble("capacity_kg"),
                        rs.getString("fuel_type"),
                        rs.getString("status"),
                        rs.getDate("last_maintenance_date").toLocalDate(),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                )
            );
        } catch (Exception e) {
            return null;
        }
    }
}

