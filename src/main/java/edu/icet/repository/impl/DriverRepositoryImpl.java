package edu.icet.repository.impl;

import edu.icet.model.Driver;
import edu.icet.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DriverRepositoryImpl implements DriverRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Driver> getAll() {
        List<Driver> driverList = jdbcTemplate.query("SELECT * FROM drivers", (rs, rowNum) -> {
            return new Driver(
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
            );
        });
        return driverList;
    }
}
