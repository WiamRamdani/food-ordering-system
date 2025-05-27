package com.wm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wm.model.Category;
import com.wm.model.Restaurants;
import com.wm.model.Admin;
import com.wm.model.plats;
import com.wm.repository.CategoryRepository;
import com.wm.repository.RestaurantRepository;
import com.wm.service.AdminService;
import com.wm.service.FoodService;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private AdminService utilisateurService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantRepository restaurantRepository; 


    @GetMapping("/restaurant/{id_restaurant}")
    public ResponseEntity<List<plats>> getRestaurantFood(
        @RequestParam(required = false) boolean seasonal,
        @RequestParam(required = false) boolean vegetarian,
        @RequestParam(name="food_category",required = false) String categoryName,
        @PathVariable Long id_restaurant,
        @RequestHeader("Authorization") String jwt) throws Exception{
            Admin utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
            Category category = null;
            if (categoryName != null && !categoryName.isBlank()) {
                 category = categoryRepository.findByNomAndRestaurantId(categoryName,id_restaurant).orElse(null); // 🔍 recherche à faire
            }

            List<plats> plats=foodService.getRestaurantsPlats(id_restaurant,vegetarian,seasonal, category);

            return new ResponseEntity<>(plats, HttpStatus.OK);
        }
        
    @GetMapping("/search")
    public ResponseEntity<List<plats>> searchPlats(
        @RequestParam String keyword,
        @RequestHeader("Authorization") String jwt) throws Exception{
            Admin utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);

            List<plats> plats=foodService.searchPlats(keyword);

            return new ResponseEntity<>(plats, HttpStatus.OK);
        }
}

