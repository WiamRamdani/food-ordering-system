package com.wm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.model.IngredientCategory;

public interface IngredientCategoryRepository  extends JpaRepository<IngredientCategory, Long>{

    List<IngredientCategory> findByRestaurantId(Long id_restaurant);

    Optional<IngredientCategory> findByNom(String nom);
}
