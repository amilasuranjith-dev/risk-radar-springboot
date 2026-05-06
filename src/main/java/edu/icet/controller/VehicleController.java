package edu.icet.controller;

import edu.icet.dto.VehicleRequestDto;
import edu.icet.dto.VehicleResponseDto;
import edu.icet.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/get-all")
    public List<VehicleResponseDto> getAll() {
        return vehicleService.getAll();
    }

    @PostMapping("/add")
    public boolean add(@RequestBody VehicleRequestDto vehicleRequest) {
        return vehicleService.add(vehicleRequest);
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable Long id, @RequestBody VehicleRequestDto vehicleRequest) {
        return vehicleService.update(id, vehicleRequest);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id) {
        return vehicleService.deleteById(id);
    }

    @GetMapping("/searchById/{id}")
    public VehicleResponseDto searchById(@PathVariable Long id) {
        return vehicleService.searchById(id);
    }
}

