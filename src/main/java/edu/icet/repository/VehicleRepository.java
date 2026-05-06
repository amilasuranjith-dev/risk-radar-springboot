package edu.icet.repository;

import edu.icet.model.Vehicle;

import java.util.List;

public interface VehicleRepository {
    List<Vehicle> getAll();
    boolean add(Vehicle vehicle);
    boolean update(Vehicle vehicle);
    boolean deleteById(Long id);
    Vehicle searchById(Long id);
}

