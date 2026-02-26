package edu.icet.repository;

import edu.icet.model.Driver;
import edu.icet.model.Vehicle;

import java.util.List;

public interface DriverRepository {
    List<Driver> getAll();
}
