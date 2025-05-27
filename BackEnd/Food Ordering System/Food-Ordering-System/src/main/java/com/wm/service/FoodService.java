package com.wm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.wm.model.Category;
import com.wm.model.Restaurants;
import com.wm.model.plats;
import com.wm.request.CreateFoodRequest;

public interface FoodService {

    public plats createPlats(CreateFoodRequest req, Category category, Restaurants restaurant);

    void deleteFood(Long id_plat) throws Exception;

    public List<plats> getRestaurantsPlats(Long id_restaurant,boolean vegetarian,boolean seasonal, Category category);

    public List<plats> searchPlats(String Keyword);

    public plats findPlatsById(Long id_plat) throws Exception;

    public plats findPlatsByNom(String nom_plat) throws Exception;

    public plats updateAvailibilityStatus(Long id_plat) throws Exception;

    public List<plats> filterBySeasonal(List<plats> plats, boolean isSeasonal) ;
    public List<plats> filterByVeg(List<plats> plats, boolean isVeg);

    public plats findByNomAndRestaurantId(String nom, Long restaurantId) throws Exception;

}
