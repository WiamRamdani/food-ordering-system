package com.wm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.model.Category;
import com.wm.model.IngredientCategory;
import com.wm.model.plats;
import com.wm.model.Restaurants;
import com.wm.model.ingredients;
import com.wm.repository.FoodRepository;
import com.wm.repository.IngredientCategoryRepository;
import com.wm.repository.IngredientRepository;
import com.wm.request.CreateFoodRequest;



@Service
public class FoodServiceImp implements FoodService{

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Override
    public plats createPlats(CreateFoodRequest req, Category category, Restaurants restaurant) {
        plats plat = new plats();
        plat.setCategorie(category);
        plat.setRestaurant(restaurant);
        plat.setDescription(req.getDescription());
        plat.setImage(req.getImage());
        plat.setNom(req.getNom());
        plat.setPrix(req.getPrix());
        plat.setQuantite(req.getQuantite());
        plat.setVegetarian(req.isVegetarian());
        plat.setSeasonal(req.isSeasonal());
        plat.setDisponible(req.isDisponible());
        if (!restaurant.getCategories().contains(category)) {
            restaurant.getCategories().add(category);
        }

        List<ingredients> ingredientsList = new ArrayList<>();

        for (ingredients ing : req.getIngredients()) {
            // Rechercher ou créer la catégorie d'ingrédient
            IngredientCategory cat = ingredientCategoryRepository
                .findByNom(ing.getCategory().getNom())
                // .orElseGet(() -> ingredientCategoryRepository.save(ing.getCategory()));
                .orElseGet(()->{
                    IngredientCategory newCategory = new IngredientCategory();
                    newCategory.setNom(ing.getCategory().getNom());
                    newCategory.setRestaurant(restaurant);
                    return ingredientCategoryRepository.save(newCategory);
                });

            ing.setCategory(cat);
            ing.setPlat(plat);
            ing.setRestaurant(restaurant); // Associer l'ingrédient au plat
            ingredientsList.add(ing);
        }

        plat.setIngredients(ingredientsList);

        plats savedFood = foodRepository.save(plat); // Grâce au cascade, les ingrédients seront aussi sauvegardés

        restaurant.getPlats().add(savedFood);
        return savedFood;
     }

    @Override
    public void deleteFood(Long id_plat) throws Exception {
        
        plats plat = findPlatsById(id_plat);
        plat.setRestaurant(null);
        foodRepository.save(plat);
    }

    @Override
    public plats findPlatsById(Long id_plat) throws Exception {
        Optional<plats> optionalPlat=foodRepository.findById(id_plat);

        if(optionalPlat.isEmpty()){
            throw new Exception("food doesn't exist");
        }
        return optionalPlat.get();
    }

    @Override
    public List<plats> getRestaurantsPlats(Long id_restaurant,boolean vegetarian,boolean seasonal, Category category) {
        
        List<plats> plats = foodRepository.findByRestaurantId(id_restaurant);
        if(category!=null && !category.equals("")){
            plats = filterByCategory(plats, category);
        }
        if (seasonal) {
        plats = filterBySeasonal(plats, true);
    }

         if (vegetarian ) {
            plats = filterByVeg(plats, true);
        } else if (!vegetarian ) {
            plats = filterByVeg(plats, false);
        }
        return plats;
    }

    public List<plats> filterByCategory(List<plats> plats, Category category) {
        return plats.stream().filter(plat -> {
            if(plat.getCategorie()!=null){
                return plat.getCategorie().equals(category);
            }
            return false;
        }).collect(Collectors.toList());
    }

    
    public List<plats> filterBySeasonal(List<plats> plats, boolean isSeasonal) {
        return plats.stream().filter(plat -> plat.isSeasonal()).collect(Collectors.toList());
    }

    public List<plats> filterByVeg(List<plats> plats, boolean isVeg) {
        return plats.stream()
            .filter(plat -> isVeg == plat.isVegetarian())
            .collect(Collectors.toList());
    }

    @Override
    public List<plats> searchPlats(String Keyword) {
        
        return foodRepository.searchPlats(Keyword);
    }

    @Override
    public plats updateAvailibilityStatus(Long id_plat) throws Exception {
        plats plat = findPlatsById(id_plat);
        plat.setDisponible(!plat.isDisponible());
        return foodRepository.save(plat);
    }

    @Override
    public plats findPlatsByNom(String nom) throws Exception {
        Optional<plats> opt = foodRepository.findByNom(nom);
        if(!opt.isPresent()){
            throw new Exception("food not found with name: "+nom);
        }
        return opt.get();
    }

    @Override
    public plats findByNomAndRestaurantId(String nom, Long restaurantId) throws Exception{
        Optional<plats> opt = foodRepository.findByNomAndRestaurantId(nom, restaurantId);
        if(!opt.isPresent()){
            throw new Exception("food not found with name: "+nom);
        }
        return opt.get();
    }
}
