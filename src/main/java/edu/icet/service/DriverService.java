package edu.icet.service;

import edu.icet.dto.DriverRequestDto;
import edu.icet.dto.DriverResponseDto;

import java.util.List;

public interface DriverService {
    List<DriverResponseDto> getAll();
    boolean add(DriverRequestDto driverRequest);
    boolean update(Long id, DriverRequestDto driverRequest);
    boolean deleteById(Long id);
    DriverResponseDto searchById(Long id);
}
