package edu.icet.service.impl;

import edu.icet.dto.VehicleRequestDto;
import edu.icet.dto.VehicleResponseDto;
import edu.icet.exception.DriverNotFoundException;
import edu.icet.model.Vehicle;
import edu.icet.repository.VehicleRepository;
import edu.icet.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private VehicleResponseDto mapToDto(Vehicle vehicle) {
        if (vehicle == null) return null;
        VehicleResponseDto dto = new VehicleResponseDto();
        dto.setId(vehicle.getId());
        dto.setLicensePlate(vehicle.getLicensePlate());
        dto.setModel(vehicle.getModel());
        dto.setManufacturer(vehicle.getManufacturer());
        dto.setYear(vehicle.getYear());
        dto.setVehicleType(vehicle.getVehicleType());
        dto.setCapacityKg(vehicle.getCapacityKg());
        dto.setFuelType(vehicle.getFuelType());
        dto.setStatus(vehicle.getStatus());
        dto.setLastMaintenanceDate(vehicle.getLastMaintenanceDate());
        dto.setCreatedAt(vehicle.getCreatedAt());
        dto.setUpdatedAt(vehicle.getUpdatedAt());
        return dto;
    }

    private Vehicle mapToEntity(VehicleRequestDto dto) {
        if (dto == null) return null;
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(dto.getLicensePlate());
        vehicle.setModel(dto.getModel());
        vehicle.setManufacturer(dto.getManufacturer());
        vehicle.setYear(dto.getYear());
        vehicle.setVehicleType(dto.getVehicleType());
        vehicle.setCapacityKg(dto.getCapacityKg());
        vehicle.setFuelType(dto.getFuelType());
        vehicle.setStatus(dto.getStatus());
        vehicle.setLastMaintenanceDate(dto.getLastMaintenanceDate());
        return vehicle;
    }

    @Override
    public List<VehicleResponseDto> getAll() {
        return vehicleRepository.getAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean add(VehicleRequestDto vehicleRequest) {
        Vehicle vehicle = mapToEntity(vehicleRequest);
        vehicle.setCreatedAt(LocalDateTime.now());
        vehicle.setUpdatedAt(LocalDateTime.now());
        return vehicleRepository.add(vehicle);
    }

    @Override
    public boolean update(Long id, VehicleRequestDto vehicleRequest) {
        Vehicle existing = vehicleRepository.searchById(id);
        if (existing == null) {
            throw new DriverNotFoundException("Vehicle not found with id: " + id);
        }

        existing.setLicensePlate(vehicleRequest.getLicensePlate());
        existing.setModel(vehicleRequest.getModel());
        existing.setManufacturer(vehicleRequest.getManufacturer());
        existing.setYear(vehicleRequest.getYear());
        existing.setVehicleType(vehicleRequest.getVehicleType());
        existing.setCapacityKg(vehicleRequest.getCapacityKg());
        existing.setFuelType(vehicleRequest.getFuelType());
        existing.setStatus(vehicleRequest.getStatus());
        existing.setLastMaintenanceDate(vehicleRequest.getLastMaintenanceDate());
        existing.setUpdatedAt(LocalDateTime.now());

        return vehicleRepository.update(existing);
    }

    @Override
    public boolean deleteById(Long id) {
        Vehicle existing = vehicleRepository.searchById(id);
        if (existing == null) {
            throw new DriverNotFoundException("Vehicle not found with id: " + id);
        }
        return vehicleRepository.deleteById(id);
    }

    @Override
    public VehicleResponseDto searchById(Long id) {
        Vehicle vehicle = vehicleRepository.searchById(id);
        if (vehicle == null) {
            throw new DriverNotFoundException("Vehicle not found with id: " + id);
        }
        return mapToDto(vehicle);
    }
}




