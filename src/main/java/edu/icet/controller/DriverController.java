package edu.icet.controller;

import edu.icet.model.Driver;
import edu.icet.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/get-all")
    public List<Driver> getAll(){
        return driverService.getAll();
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Driver driver){
        return driverService.add(driver);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Driver driver){
        return driverService.update(driver);
    }

    @DeleteMapping("/deleteById/{id}")
    public boolean  dealetById(@PathVariable Long id){
        return driverService.deleteById(id);
    }

    @GetMapping("/searchById/{id}")
    public Driver searchById(Long id){
        return driverService.searchById(id);
    }



}
