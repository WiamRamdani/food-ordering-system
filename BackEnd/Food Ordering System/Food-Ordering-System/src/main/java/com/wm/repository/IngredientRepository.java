package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wm.model.ingredients;

public interface IngredientRepository extends JpaRepository<ingredients, Long>{

    List<ingredients> findByRestaurant_Id(Long restaurantId);

    @Query("SELECT i FROM ingredients i WHERE i.plat.id_plat = :id")
    List<ingredients> findByPlatId(@Param("id") Long id);

    @Query("SELECT i FROM ingredients i WHERE i.nom IN :noms AND i.restaurant.id = :restaurantId")
    List<ingredients> findByNomInAndRestaurantId(@Param("noms") List<String> noms, @Param("restaurantId") Long restaurantId);

}
