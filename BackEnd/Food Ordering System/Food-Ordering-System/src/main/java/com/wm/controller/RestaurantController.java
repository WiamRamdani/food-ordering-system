package com.wm.controller;

import com.wm.model.Restaurants;
import com.wm.model.utilisateur;
import com.wm.service.RestaurantService;
import com.wm.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurants>> searchRestaurant(
            @RequestHeader("Authorization") String jwt,
            @RequestParam String Keyword
    ) throws Exception {
        utilisateur utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);

        List<Restaurants> restaurant = restaurantService.searchRestaurant(Keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<List<Restaurants>> getAllRestaurants(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        utilisateur utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);

        List<Restaurants> restaurant=restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurants> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        utilisateur utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);

        Restaurants restaurant=restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<Restaurants> addToFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        utilisateur utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);
        Restaurants restaurant=restaurantService.addToFavorites(id,utilisateur);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
