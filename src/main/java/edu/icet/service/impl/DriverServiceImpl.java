package edu.icet.service.impl;

import edu.icet.dto.DriverRequestDto;
import edu.icet.dto.DriverResponseDto;
import edu.icet.exception.DriverNotFoundException;
import edu.icet.model.Driver;
import edu.icet.repository.DriverRepository;
import edu.icet.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    private DriverResponseDto mapToDto(Driver driver) {
        if (driver == null) return null;
        DriverResponseDto dto = new DriverResponseDto();
        dto.setId(driver.getId());
        dto.setLicenseNumber(driver.getLicenseNumber());
        dto.setFirstName(driver.getFirstName());
        dto.setLastName(driver.getLastName());
        dto.setPhone(driver.getPhone());
        dto.setEmail(driver.getEmail());
        dto.setAddress(driver.getAddress());
        dto.setHireDate(driver.getHireDate());
        dto.setStatus(driver.getStatus());
        dto.setRating(driver.getRating());
        dto.setTotalTrips(driver.getTotalTrips());
        dto.setCreatedAt(driver.getCreatedAt());
        dto.setUpdateAt(driver.getUpdateAt());
        return dto;
    }

    private Driver mapToEntity(DriverRequestDto dto) {
        if (dto == null) return null;
        Driver driver = new Driver();
        driver.setLicenseNumber(dto.getLicenseNumber());
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setPhone(dto.getPhone());
        driver.setEmail(dto.getEmail());
        driver.setAddress(dto.getAddress());
        driver.setHireDate(dto.getHireDate());
        driver.setStatus(dto.getStatus());
        driver.setRating(dto.getRating());
        driver.setTotalTrips(dto.getTotalTrips());
        return driver;
    }

    @Override
    public List<DriverResponseDto> getAll() {
        return driverRepository.getAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean add(DriverRequestDto driverRequest) {
        Driver driver = mapToEntity(driverRequest);
        driver.setCreatedAt(LocalDateTime.now());
        driver.setUpdateAt(LocalDateTime.now());
        return driverRepository.add(driver);
    }

    @Override
    public boolean update(Long id, DriverRequestDto driverRequest) {
        Driver existing = driverRepository.seachById(id);
        if (existing == null) {
            throw new DriverNotFoundException("Driver not found with id: " + id);
        }

        existing.setLicenseNumber(driverRequest.getLicenseNumber());
        existing.setFirstName(driverRequest.getFirstName());
        existing.setLastName(driverRequest.getLastName());
        existing.setPhone(driverRequest.getPhone());
        existing.setEmail(driverRequest.getEmail());
        existing.setAddress(driverRequest.getAddress());
        existing.setHireDate(driverRequest.getHireDate());
        existing.setStatus(driverRequest.getStatus());
        existing.setRating(driverRequest.getRating());
        existing.setTotalTrips(driverRequest.getTotalTrips());
        existing.setUpdateAt(LocalDateTime.now());

        return driverRepository.update(existing);
    }

    @Override
    public boolean deleteById(Long id) {
        Driver existing = driverRepository.seachById(id);
        if (existing == null) {
            throw new DriverNotFoundException("Driver not found with id: " + id);
        }
        return driverRepository.deleteById(id);
    }

    @Override
    public DriverResponseDto searchById(Long id) {
        Driver driver = driverRepository.seachById(id);
        if (driver == null) {
            throw new DriverNotFoundException("Driver not found with id: " + id);
        }
        return mapToDto(driver);
    }
}
