package edu.icet.repository.impl;

import edu.icet.model.Alert;
import edu.icet.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlertRepositoryImpl implements AlertRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean save(Alert alert) {
        LocalDateTime createdAt = alert.getCreatedAt() != null ? alert.getCreatedAt() : LocalDateTime.now();
        String sql = "INSERT INTO alerts (shipment_id, vehicle_id, alert_type, severity, message, is_resolved, resolved_at, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                alert.getShipmentId(),
                alert.getVehicleId(),
                alert.getAlertType(),
                alert.getSeverity(),
                alert.getMessage(),
                alert.getIsResolved() != null ? alert.getIsResolved() : false,
                alert.getResolvedAt() != null ? Timestamp.valueOf(alert.getResolvedAt()) : null,
                Timestamp.valueOf(createdAt)
        ) > 0;
    }

    @Override
    public List<Alert> getActiveAlerts() {
        String sql = "SELECT * FROM alerts WHERE is_resolved = false ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Alert(
                rs.getLong("id"),
                rs.getLong("shipment_id"),
                rs.getLong("vehicle_id"),
                rs.getString("alert_type"),
                rs.getString("severity"),
                rs.getString("message"),
                rs.getBoolean("is_resolved"),
                rs.getTimestamp("resolved_at") != null ? rs.getTimestamp("resolved_at").toLocalDateTime() : null,
                rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null
        ));
    }

    @Override
    public boolean resolveAlert(Long id) {
        String sql = "UPDATE alerts SET is_resolved = true, resolved_at = ? WHERE id = ?";
        return jdbcTemplate.update(sql, Timestamp.valueOf(LocalDateTime.now()), id) > 0;
    }
}
