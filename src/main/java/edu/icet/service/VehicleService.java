package edu.icet.service;

import edu.icet.dto.VehicleRequestDto;
import edu.icet.dto.VehicleResponseDto;

import java.util.List;

public interface VehicleService {
    List<VehicleResponseDto> getAll();
    boolean add(VehicleRequestDto vehicleRequest);
    boolean update(Long id, VehicleRequestDto vehicleRequest);
    boolean deleteById(Long id);
    VehicleResponseDto searchById(Long id);
}

