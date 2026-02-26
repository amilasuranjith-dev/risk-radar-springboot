package edu.icet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Long id;
    private String username;
    private String passwordHash;
    private String email;
    private String fullname;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private boolean isActive;
}
