package edu.icet.controller;

import edu.icet.dto.DriverRequestDto;
import edu.icet.dto.DriverResponseDto;
import edu.icet.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/get-all")
    public ResponseEntity<List<DriverResponseDto>> getAll(){
        return ResponseEntity.ok(driverService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> add(@RequestBody DriverRequestDto driverRequest){
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(driverService.add(driverRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @RequestBody DriverRequestDto driverRequest){
        return ResponseEntity.ok(driverService.update(id, driverRequest));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(driverService.deleteById(id));
    }

    @GetMapping("/searchById/{id}")
    public ResponseEntity<DriverResponseDto> searchById(@PathVariable Long id){
        return ResponseEntity.ok(driverService.searchById(id));
    }

}
