package com.wm.repository;

import com.wm.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Adresse, Long> {

}
