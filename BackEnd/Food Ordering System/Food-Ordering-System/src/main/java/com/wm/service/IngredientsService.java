package com.wm.service;

import java.util.List;

import com.wm.model.IngredientCategory;
import com.wm.model.ingredients;

public interface IngredientsService {

    public IngredientCategory createIngredientCategory(String nom, String nom_restaurant) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public ingredients createIngredient(String nom_restaurant, String nom_ingr, String nom_category ) throws Exception;

    public List<ingredients> findRestaurantIngredients(Long id_restaurant);

    public ingredients updateStock(Long id) throws Exception;

    public List<ingredients> findFoodIngredients(Long id_food);
}
