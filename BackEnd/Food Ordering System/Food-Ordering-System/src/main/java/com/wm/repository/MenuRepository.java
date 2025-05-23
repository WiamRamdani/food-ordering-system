package com.wm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wm.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>{


    @Query("SELECT c FROM Menu c WHERE c.client.id_utilisateur = :id")
    Menu findByClientId(@Param("id") Long id);

}
