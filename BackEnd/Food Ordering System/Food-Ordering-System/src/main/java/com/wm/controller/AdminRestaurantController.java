package com.wm.controller;

import com.wm.model.ROLE;
import com.wm.model.Restaurants;
import com.wm.model.Admin;
import com.wm.request.CreateRestaurantRequest;
import com.wm.response.MessageResponse;
import com.wm.service.AdminService;
import com.wm.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private AdminService utilisateurService;

    @PostMapping()
    public ResponseEntity<?> createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        if (jwt == null || !jwt.startsWith("Bearer ")) {
            return new ResponseEntity<>("Authorization header must start with Bearer", HttpStatus.UNAUTHORIZED);
        }
        jwt=jwt.substring(7).trim();
        Admin utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);

        ROLE role=utilisateur.getRole();
        System.out.println("Role de l'utilisateur : " + role);
        if (role == null || (!role.name().equals("ADMIN") && !role.name().equals("RESTAURANT_OWNER"))) {
            return new ResponseEntity<>("You are not authorized to create a restaurant", HttpStatus.FORBIDDEN);
        }

        Restaurants restaurant=restaurantService.createRestaurant(req, utilisateur);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id_restaurant}")
    public ResponseEntity<?> updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id_restaurant
    ) throws Exception {
        Admin utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);
        ROLE role=utilisateur.getRole();
        if(role!=ROLE.ADMIN && role!=ROLE.RESTAURANT_OWNER) {
            return new ResponseEntity<>("You are not authorized to update a restaurant", HttpStatus.FORBIDDEN);
        }
        Restaurants restaurant=restaurantService.updateRestaurant(id_restaurant,req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id_restaurant}")
    public ResponseEntity<?> deleteRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id_restaurant
    ) throws Exception {
        Admin utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);
        ROLE role=utilisateur.getRole();
        if(role!=ROLE.ADMIN ) {
            return new ResponseEntity<>("You are not authorized to delete a restaurant", HttpStatus.FORBIDDEN);
        }
        restaurantService.deleteRestaurant(id_restaurant);
        MessageResponse response=new MessageResponse();
        response.setMessage("Restaurant deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id_restaurant}/status")
    public ResponseEntity<?> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id_restaurant
    ) throws Exception {
        Admin utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);
        ROLE role=utilisateur.getRole();
        if(role!=ROLE.ADMIN && role!=ROLE.RESTAURANT_OWNER) {
            return new ResponseEntity<>("You are not authorized to create a restaurant", HttpStatus.FORBIDDEN);
        }
        Restaurants restaurant=restaurantService.updateRestaurantStatus(id_restaurant);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> findRestaurantByUserId(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        Admin utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);
        ROLE role=utilisateur.getRole();
        if(role!=ROLE.ADMIN && role!=ROLE.RESTAURANT_OWNER) {
            return new ResponseEntity<>("You are not authorized to create a restaurant", HttpStatus.FORBIDDEN);
        }
        Restaurants restaurant=restaurantService.getRestaurantByUserId(utilisateur.getIdAdmin());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


}
