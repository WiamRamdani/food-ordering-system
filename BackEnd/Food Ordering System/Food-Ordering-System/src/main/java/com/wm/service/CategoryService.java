package com.wm.service;

import java.util.List;


import com.wm.model.Category;

public interface CategoryService {

    public Category createCategory(String nom, Long id_utilisateur) throws Exception;

    public List<Category> findCategoryByRestaurantId(Long id) throws Exception;
    
    public Category findCategoryById(Long id) throws Exception;

    public Category findCategoryByNom(String nom) throws Exception;

    public Category findCategoryByNomAndRestaurnt(String nom, Long id_restaurant) throws Exception;


}
