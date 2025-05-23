package com.wm.repository;

import com.wm.model.admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<admin ,Long> {
    admin findByEmail(String email);
    Boolean existsByEmail(String email);
}
