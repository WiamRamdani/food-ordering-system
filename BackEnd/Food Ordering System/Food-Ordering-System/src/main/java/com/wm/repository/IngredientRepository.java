package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.model.ingredients;

public interface IngredientRepository extends JpaRepository<ingredients, Long>{

    List<ingredients> findByRestaurant_Id(Long restaurantId);


}
