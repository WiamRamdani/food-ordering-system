package com.wm.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wm.model.Menu;
import com.wm.model.plats;

public interface MenuRepository extends JpaRepository<Menu, Long>{


    @Query("SELECT c FROM Menu c WHERE c.client.idAdmin = :id")
    Menu findByClientIdAdmin(@Param("id") Long id);

    Menu  findByRestaurantId(Long id_restaurant);

    Optional<Menu> findByRestaurant_IdAndClient_IdAdmin(Long restaurantId, Long clientIdAdmin);

}
