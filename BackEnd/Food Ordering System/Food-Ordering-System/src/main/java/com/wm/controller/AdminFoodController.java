package com.wm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.model.plats;
import com.wm.model.Category;
import com.wm.model.Restaurants;
import com.wm.model.Admin;
import com.wm.request.CreateFoodRequest;
import com.wm.response.MessageResponse;
import com.wm.service.AdminServiceImp;
import com.wm.service.CategoryService;
import com.wm.service.FoodService;
import com.wm.service.RestaurantService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private AdminServiceImp utilisateurService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<plats> createFoodRequest(@RequestBody CreateFoodRequest req,
                                            @RequestHeader("Authorization") String jwt) throws Exception{
        Admin utilisateur =utilisateurService.findUtilisateurByJwtToken(jwt);
        Restaurants restaurant =restaurantService.findRestaurantByNom(req.getRestaurant().getNom());
        Category category = categoryService.findCategoryByNomAndRestaurnt(req.getCategorie().getNom(),restaurant.getId());
        plats plat=foodService.createPlats(req, category, restaurant);
        return new ResponseEntity<>(plat, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception{
        Admin utilisateur =utilisateurService.findUtilisateurByJwtToken(jwt);
        foodService.deleteFood(id);

        MessageResponse res = new MessageResponse();
        res.setMessage("food deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<plats> updateFoodAvailibilityStatus(@PathVariable Long id,
                                                      @RequestHeader("Authorization") String jwt) throws Exception{
        Admin utilisateur =utilisateurService.findUtilisateurByJwtToken(jwt);
        
        plats plat =foodService.updateAvailibilityStatus(id);

        return new ResponseEntity<>(plat, HttpStatus.CREATED);
    }
}