package com.wm.controller;

import java.util.List;

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
import com.wm.model.plats;
import com.wm.model.utilisateur;
import com.wm.service.FoodService;
import com.wm.service.UtilisateurService;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UtilisateurService utilisateurService;


    @GetMapping("/restaurant/{id_restaurant}")
    public ResponseEntity<List<plats>> getRestaurantFood(
        @RequestParam(required = false) Category categorie,
        @PathVariable Long id_restaurant,
        @RequestHeader("Authorization") String jwt) throws Exception{
            utilisateur utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);

            List<plats> plats=foodService.getRestaurantsPlats(id_restaurant, categorie);

            return new ResponseEntity<>(plats, HttpStatus.OK);
        }
}

