package edu.icet.repository;

import edu.icet.model.Alert;

import java.util.List;

public interface AlertRepository {
    boolean save(Alert alert);
    List<Alert> getActiveAlerts();
    boolean resolveAlert(Long id);
}
