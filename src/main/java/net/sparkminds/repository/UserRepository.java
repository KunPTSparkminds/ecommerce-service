package net.sparkminds.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sparkminds.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    
    Optional<User> findOneByEmailIgnoreCaseAndPassword(String email, String password);
    
    Optional<User> findByEmail(String email);
    
}
