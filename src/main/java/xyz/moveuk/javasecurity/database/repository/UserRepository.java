package xyz.moveuk.javasecurity.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.moveuk.javasecurity.database.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}