package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.model.IngredientCategory;
import com.wm.model.ingredients;
import com.wm.request.IngredientCategoryRequest;
import com.wm.request.IngredientRequest;
import com.wm.service.IngredientsService;

@RestController
@RequestMapping("/api")
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/admin/ingredients/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req) throws Exception{
        IngredientCategory category = ingredientsService.createIngredientCategory(req.getNom(), req.getNom_restaurant());
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping("/admin/ingredients")
    public ResponseEntity<ingredients> createIngredient(@RequestBody IngredientRequest req) throws Exception{
        ingredients item = ingredientsService.createIngredient(req.getNom_restaurant(),req.getNom(), req.getNom_category());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/admin/ingredients/{id}/stock")
    public ResponseEntity<ingredients> updateIngredientStock(@PathVariable Long id) throws Exception{
        ingredients item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/ingredients/restaurant/{id}")
    public ResponseEntity<List<ingredients>> getRestaurantIngredients(@PathVariable Long id) throws Exception{
        List<ingredients> items = ingredientsService.findRestaurantIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/ingredients/food/{id}")
    public ResponseEntity<List<ingredients>> getFoodIngredients(@PathVariable Long id) throws Exception{
        List<ingredients> items = ingredientsService.findFoodIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientsCategory(@PathVariable Long id) throws Exception{
        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }



}
