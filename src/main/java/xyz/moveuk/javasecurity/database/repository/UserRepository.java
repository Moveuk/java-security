package xyz.moveuk.javasecurity.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.moveuk.javasecurity.database.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}