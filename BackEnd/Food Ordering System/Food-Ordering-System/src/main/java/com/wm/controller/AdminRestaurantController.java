package com.wm.controller;

import com.wm.model.Restaurants;
import com.wm.model.utilisateur;
import com.wm.request.CreateRestaurantRequest;
import com.wm.response.MessageResponse;
import com.wm.service.RestaurantService;
import com.wm.service.UtilisateurService;
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
    private UtilisateurService utilisateurService;

    @PostMapping()
    public ResponseEntity<Restaurants> createRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        utilisateur utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);

        Restaurants restaurant=restaurantService.createRestaurant(req, utilisateur);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id_restaurant}")
    public ResponseEntity<Restaurants> updateRestaurant(
            @RequestBody CreateRestaurantRequest req,
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id_restaurant
    ) throws Exception {
        utilisateur utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);

        Restaurants restaurant=restaurantService.updateRestaurant(id_restaurant,req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id_restaurant}")
    public ResponseEntity<MessageResponse> deleteRestaurant(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id_restaurant
    ) throws Exception {
        utilisateur utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);

        restaurantService.deleteRestaurant(id_restaurant);
        MessageResponse response=new MessageResponse();
        response.setMessage("Restaurant deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id_restaurant}/status")
    public ResponseEntity<Restaurants> updateRestaurantStatus(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id_restaurant
    ) throws Exception {
        utilisateur utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);

        Restaurants restaurant=restaurantService.updateRestaurantStatus(id_restaurant);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Restaurants> findRestaurantByUserId(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        utilisateur utilisateur=utilisateurService.findUtilisateurByJwtToken(jwt);

        Restaurants restaurant=restaurantService.getRestaurantByUserId(utilisateur.getId_utilisateur());
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


}
