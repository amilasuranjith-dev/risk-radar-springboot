package edu.icet.repository;

import edu.icet.model.LocationTracking;

import java.util.List;

public interface LocationTrackingRepository {
    boolean save(LocationTracking location);
    List<LocationTracking> getByShipmentId(Long shipmentId);
}
