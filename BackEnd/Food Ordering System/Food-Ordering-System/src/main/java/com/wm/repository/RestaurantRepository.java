package com.wm.repository;

import com.wm.model.Owner;
import com.wm.model.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurants,Long> {

    @Query("SELECT r FROM Restaurants r where lower(r.nom) like lower(concat('%',:query,'%'))" +
            "or lower(r.Type_cuisine) like lower(concat('%',:query,'%')) ")
    List<Restaurants> findBySearchQuery(String query);

    Restaurants findByOwner_IdAdmin(Long id);

    Optional<Restaurants> findById(Long id);

    Optional<Restaurants> findByNom(String nom);
}
