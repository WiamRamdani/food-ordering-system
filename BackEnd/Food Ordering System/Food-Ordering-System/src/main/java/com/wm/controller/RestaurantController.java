package com.wm.controller;

import com.wm.model.Restaurants;
import com.wm.model.Admin;
import com.wm.service.AdminService;
import com.wm.service.RestaurantService;
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
    private AdminService utilisateurService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurants>> searchRestaurant(
            // @RequestHeader("Authorization") String jwt,
            @RequestParam String Keyword
    ) throws Exception {
        
        // admin utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);

        List<Restaurants> restaurant = restaurantService.searchRestaurant(Keyword);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<?> getAllRestaurants(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        if (jwt == null || !jwt.startsWith("Bearer ")) {
            return new ResponseEntity<>("Authorization header must start with Bearer", HttpStatus.UNAUTHORIZED);
        }
        jwt=jwt.substring(7).trim();
        Admin utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);
        if (utilisateur == null) {
            return new ResponseEntity<>("Invalid or expired token", HttpStatus.UNAUTHORIZED);
        }
        List<Restaurants> restaurant=restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findRestaurantById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        if (jwt == null || !jwt.startsWith("Bearer ")) {
            return new ResponseEntity<>("Authorization header must start with Bearer", HttpStatus.UNAUTHORIZED);
        }
        jwt=jwt.substring(7).trim();
        Admin utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);
        if (utilisateur == null) {
            return new ResponseEntity<>("Invalid or expired token", HttpStatus.UNAUTHORIZED);
        }
        Restaurants restaurant=restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<?> addToFavorites(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws Exception {
        System.out.println("Token brut reçu : '" + jwt + "'");
        if (jwt == null || !jwt.startsWith("Bearer ")) {
            return new ResponseEntity<>("Authorization header must start with Bearer", HttpStatus.UNAUTHORIZED);
        }
        
        System.out.println("Token nettoyé : '" + jwt + "'");
        jwt=jwt.substring(7).trim();
        Admin utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);
        if (utilisateur == null) {
            return new ResponseEntity<>("Invalid or expired token", HttpStatus.UNAUTHORIZED);
        }
        Restaurants restaurant=restaurantService.addToFavorites(id,utilisateur);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
