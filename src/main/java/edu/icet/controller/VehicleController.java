package edu.icet.controller;

import edu.icet.dto.VehicleRequestDto;
import edu.icet.dto.VehicleResponseDto;
import edu.icet.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/get-all")
    public ResponseEntity<List<VehicleResponseDto>> getAll() {
        return ResponseEntity.ok(vehicleService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody VehicleRequestDto vehicleRequest) {
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(vehicleService.add(vehicleRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody VehicleRequestDto vehicleRequest) {
        return ResponseEntity.ok(vehicleService.update(id, vehicleRequest));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.deleteById(id));
    }

    @GetMapping("/searchById/{id}")
    public VehicleResponseDto searchById(@PathVariable Long id) {
        return vehicleService.searchById(id);
    }
}

