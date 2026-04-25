package edu.icet.repository.impl;

import edu.icet.model.Driver;
import edu.icet.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DriverRepositoryImpl implements DriverRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Driver> getAll() {
        return jdbcTemplate.query("SELECT * FROM drivers", (rs, rowNum) ->
            new Driver(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getDate(8).toLocalDate(),
                    rs.getString(9),
                    rs.getDouble(10),
                    rs.getInt(11),
                    rs.getTimestamp(12).toLocalDateTime(),
                    rs.getTimestamp(13).toLocalDateTime()
            )
        );
    }

    @Override
    public boolean add(Driver driver) {
        return jdbcTemplate.update("INSERT INTO drivers (license_number, first_name, last_name, phone, email, address, hire_date, status, rating, total_trips, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                driver.getLicenseNumber(),
                driver.getFirstName(),
                driver.getLastName(),
                driver.getPhone(),
                driver.getEmail(),
                driver.getAddress(),
                driver.getHireDate(),
                driver.getStatus(),
                driver.getRating(),
                driver.getTotalTrips(),
                Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now())
                ) > 0;
    }

    @Override
    public boolean update(Driver driver) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Driver seachById(Long id) {
        return null;
    }
}
