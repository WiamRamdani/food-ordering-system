package com.wm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.model.IngredientCategory;
import com.wm.model.ingredients;
import com.wm.model.Restaurants;
import com.wm.repository.IngredientCategoryRepository;
import com.wm.repository.IngredientRepository;

@Service
public class IngredientServiceImp implements IngredientsService{

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String nom, Long id_restau) throws Exception {
        Restaurants restaurant = restaurantService.findRestaurantById(id_restau);

        IngredientCategory category = new IngredientCategory();
        category.setNom(nom);
        category.setRestaurant(restaurant);

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
       restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public ingredients createIngredient(Long id_restaurant, String nom_ingr, Long id_category) throws Exception {
        Restaurants restaurant=restaurantService.findRestaurantById(id_restaurant);
        IngredientCategory category=findIngredientCategoryById(id_category);

        ingredients ingredient= new ingredients();
        ingredient.setNom(nom_ingr);
        ingredient.setCategory(category);
        ingredient.setRestaurant(restaurant);

        ingredients itemIngredients = ingredientRepository.save(ingredient);
        category.getIngredients().add(itemIngredients);

        return itemIngredients;
    }

    @Override
    public List<ingredients> findRestaurantIngredients(Long id_restaurant) {
        return ingredientRepository.findByRestaurant_Id(id_restaurant);
    }

    @Override
    public ingredients updateStock(Long id) throws Exception {
        Optional<ingredients> opt=ingredientRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("ingredient not found");
        }
        ingredients ingredient=opt.get();
        ingredient.setEnStock(!ingredient.isEnStock());
        return ingredientRepository.save(ingredient);
    }
}
