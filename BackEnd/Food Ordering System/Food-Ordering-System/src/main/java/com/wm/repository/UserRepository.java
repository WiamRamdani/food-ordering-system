package com.wm.repository;

import com.wm.model.utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<utilisateur,Long> {
    utilisateur findByEmail(String email);
    Boolean existsByEmail(String email);
}
