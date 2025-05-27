package com.wm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    public List<Category>findByRestaurantId(Long id);

    Optional<Category> findByNom(String nom);

    Optional<Category> findByNomAndRestaurantId(String nom, Long restaurantId);

    

}
