package edu.icet.controller;

import edu.icet.dto.ShipmentRequestDto;
import edu.icet.dto.ShipmentResponseDto;
import edu.icet.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @GetMapping("/get-all")
    public ResponseEntity<List<ShipmentResponseDto>> getAll() {
        return ResponseEntity.ok(shipmentService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody ShipmentRequestDto shipmentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shipmentService.add(shipmentRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody ShipmentRequestDto shipmentRequest) {
        return ResponseEntity.ok(shipmentService.update(id, shipmentRequest));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(shipmentService.deleteById(id));
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<ShipmentResponseDto> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(shipmentService.searchById(id));
    }
}
