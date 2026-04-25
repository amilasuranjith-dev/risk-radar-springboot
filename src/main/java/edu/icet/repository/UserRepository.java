package edu.icet.repository;

import edu.icet.model.User;
import java.util.Optional;
import java.util.List;


public interface UserRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    boolean save(User user);
    boolean update(User user);
    List<User> getAll();
}
