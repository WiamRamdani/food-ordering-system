package com.wm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.model.Category;
import com.wm.model.Restaurants;
import com.wm.repository.CategoryRepository;

@Service
public class CategoryServiceImp  implements CategoryService{

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String nom, Long id_utilisateur) throws Exception{

        Restaurants restaurant =restaurantService.getRestaurantByUserId(id_utilisateur);
        Category category= new Category();
        category.setNom(nom);
        category.setRestaurant(restaurant);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        // Restaurants restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(id);
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            throw new Exception("category not found");
        }
        return optionalCategory.get();
    }

    @Override
    public Category findCategoryByNom(String nom) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findByNom(nom);
        if(optionalCategory.isEmpty()){
            throw new Exception("category not found with name: " + nom);
        }
        return optionalCategory.get();
    }

    @Override
    public Category findCategoryByNomAndRestaurnt(String nom, Long id_restaurant) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findByNomAndRestaurantId(nom,id_restaurant);
        if(optionalCategory.isEmpty()){
            throw new Exception("category not found with name: " + nom);
        }
        return optionalCategory.get();
    }



}
