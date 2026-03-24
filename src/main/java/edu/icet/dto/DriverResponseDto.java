package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverResponseDto {
    private Long id;
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
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}

