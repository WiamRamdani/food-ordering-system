package com.wm.service;

import java.util.List;

import com.wm.model.IngredientCategory;
import com.wm.model.ingredients;

public interface IngredientsService {

    public IngredientCategory createIngredientCategory(String nom, Long id_restau) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public ingredients createIngredient(Long id_restaurant, String nom_ingr, Long id_category ) throws Exception;

    public List<ingredients> findRestaurantIngredients(Long id_restaurant);

    public ingredients updateStock(Long id) throws Exception;
}
