package com.Travelbnb.repository;

import com.Travelbnb.entity.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppuserRepository extends JpaRepository<Appuser, Long> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Appuser> findByUsername(String username);
}