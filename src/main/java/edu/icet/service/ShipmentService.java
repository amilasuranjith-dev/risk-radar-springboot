package edu.icet.service;

import edu.icet.dto.ShipmentRequestDto;
import edu.icet.dto.ShipmentResponseDto;

import java.util.List;

public interface ShipmentService {
    List<ShipmentResponseDto> getAll();
    boolean add(ShipmentRequestDto shipmentRequest);
    boolean update(Long id, ShipmentRequestDto shipmentRequest);
    boolean deleteById(Long id);
    ShipmentResponseDto searchById(Long id);
}
