package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wm.model.plats;

@Repository
public interface FoodRepository extends JpaRepository<plats, Long> {

    List<plats> findByRestaurantId(Long id_restaurant);

    @Query("SELECT f FROM plats f WHERE f.nom LIKE %:Keyword% OR f.categorie.nom LIKE %:Keyword%")
    List<plats> searchPlats(@Param("Keyword") String Keyword);

}
