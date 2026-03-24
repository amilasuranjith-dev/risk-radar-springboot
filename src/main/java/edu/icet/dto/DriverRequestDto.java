package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequestDto {
    private String licenseNumber;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private LocalDate hireDate;
    private String status;
    private Double rating;
    private Integer totalTrips;
}

