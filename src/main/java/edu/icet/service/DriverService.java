package edu.icet.service;

import edu.icet.model.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> getAll();
    boolean add(Driver driver);
    boolean update(Driver driver);
    boolean deleteById(Long id);
    Driver searchById(Long id);
}
