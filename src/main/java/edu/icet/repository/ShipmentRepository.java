package edu.icet.repository;

import edu.icet.model.Shipment;

import java.util.List;

public interface ShipmentRepository {
    List<Shipment> getAll();
    boolean add(Shipment shipment);
    boolean update(Shipment shipment);
    boolean deleteById(Long id);
    Shipment searchById(Long id);
}
