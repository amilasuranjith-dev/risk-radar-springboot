package edu.icet.controller;

import edu.icet.dto.DriverRequestDto;
import edu.icet.dto.DriverResponseDto;
import edu.icet.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/get-all")
    public List<DriverResponseDto> getAll(){
        return driverService.getAll();
    }

    @PostMapping("/add")
    public boolean add(@RequestBody DriverRequestDto driverRequest){
        return driverService.add(driverRequest);
    }

    @PutMapping("/update/{id}")
    public boolean update(@PathVariable Long id, @RequestBody DriverRequestDto driverRequest){
        return driverService.update(id, driverRequest);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean deleteById(@PathVariable Long id){
        return driverService.deleteById(id);
    }

    @GetMapping("/searchById/{id}")
    public DriverResponseDto searchById(@PathVariable Long id){
        return driverService.searchById(id);
    }

}
