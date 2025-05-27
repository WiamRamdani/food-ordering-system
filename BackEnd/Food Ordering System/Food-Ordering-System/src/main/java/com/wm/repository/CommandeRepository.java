package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wm.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("SELECT c FROM Commande c WHERE c.client.idAdmin = :id")
    List<Commande> findByClientId(@Param("id") Long id);

    public List<Commande> findByRestaurantId(Long id_restaurant);
}
