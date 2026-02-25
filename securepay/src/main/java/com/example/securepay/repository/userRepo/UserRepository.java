package com.example.securepay.repository.userRepo;

import com.example.securepay.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    // Find by Email
    boolean existsByEmail(String email);

    // Find by Username
    boolean existsByUsername(String username);

    // Find by Number
    boolean existsByNumber(String number);
}
