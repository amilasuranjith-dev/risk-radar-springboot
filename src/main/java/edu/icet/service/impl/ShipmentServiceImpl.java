package edu.icet.service.impl;

import edu.icet.dto.ShipmentRequestDto;
import edu.icet.dto.ShipmentResponseDto;
import edu.icet.exception.ResourceNotFoundException;
import edu.icet.model.Shipment;
import edu.icet.repository.ShipmentRepository;
import edu.icet.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Override
    public List<ShipmentResponseDto> getAll() {
        List<Shipment> shipments = shipmentRepository.getAll();
        List<ShipmentResponseDto> responseList = new ArrayList<>();
        for (Shipment shipment : shipments) {
            responseList.add(mapToDto(shipment));
        }
        return responseList;
    }

    @Override
    public boolean add(ShipmentRequestDto shipmentRequest) {
        Shipment shipment = new Shipment();
        mapToEntity(shipmentRequest, shipment);
        shipment.setCreatedAt(LocalDateTime.now());
        shipment.setUpdatedAt(LocalDateTime.now());
        return shipmentRepository.add(shipment);
    }

    @Override
    public boolean update(Long id, ShipmentRequestDto shipmentRequest) {
        Shipment existing = shipmentRepository.searchById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Shipment not found with id: " + id);
        }
        mapToEntity(shipmentRequest, existing);
        existing.setUpdatedAt(LocalDateTime.now());
        return shipmentRepository.update(existing);
    }

    @Override
    public boolean deleteById(Long id) {
        Shipment existing = shipmentRepository.searchById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Shipment not found with id: " + id);
        }
        return shipmentRepository.deleteById(id);
    }

    @Override
    public ShipmentResponseDto searchById(Long id) {
        Shipment shipment = shipmentRepository.searchById(id);
        if (shipment == null) {
            throw new ResourceNotFoundException("Shipment not found with id: " + id);
        }
        return mapToDto(shipment);
    }

    private void mapToEntity(ShipmentRequestDto dto, Shipment entity) {
        entity.setShipmentNumber(dto.getShipmentNumber());
        entity.setOriginCity(dto.getOriginCity());
        entity.setOriginAddress(dto.getOriginAddress());
        entity.setDestinationCity(dto.getDestinationCity());
        entity.setDestinationAddress(dto.getDestinationAddress());
        entity.setCargoType(dto.getCargoType());
        entity.setCargoWeightKg(dto.getCargoWeightKg());
        entity.setCargoValue(dto.getCargoValue());
        entity.setStatus(dto.getStatus());
        entity.setPriority(dto.getPriority());
        entity.setScheduledPickup(dto.getScheduledPickup());
        entity.setScheduledDelivery(dto.getScheduledDelivery());
        entity.setActualPickup(dto.getActualPickup());
        entity.setActualDelivery(dto.getActualDelivery());
        entity.setVehicleId(dto.getVehicleId());
        entity.setDriverId(dto.getDriverId());
        entity.setCreatedBy(dto.getCreatedBy());
    }

    private ShipmentResponseDto mapToDto(Shipment entity) {
        return new ShipmentResponseDto(
                entity.getId(),
                entity.getShipmentNumber(),
                entity.getOriginCity(),
                entity.getOriginAddress(),
                entity.getDestinationCity(),
                entity.getDestinationAddress(),
                entity.getCargoType(),
                entity.getCargoWeightKg(),
                entity.getCargoValue(),
                entity.getStatus(),
                entity.getPriority(),
                entity.getScheduledPickup(),
                entity.getScheduledDelivery(),
                entity.getActualPickup(),
                entity.getActualDelivery(),
                entity.getVehicleId(),
                entity.getDriverId(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
