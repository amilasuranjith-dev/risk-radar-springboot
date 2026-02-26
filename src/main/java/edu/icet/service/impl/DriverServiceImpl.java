package edu.icet.service.impl;

import edu.icet.model.Driver;
import edu.icet.repository.DriverRepository;
import edu.icet.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> getAll() {
        return driverRepository.getAll();
    }
}
