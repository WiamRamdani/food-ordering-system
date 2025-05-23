package com.wm.service;

import java.util.List;
import com.wm.model.Category;
import com.wm.model.Restaurants;
import com.wm.model.plats;
import com.wm.model.Restaurants;
import com.wm.request.CreateFoodRequest;

public interface FoodService {

    public plats createPlats(CreateFoodRequest req, Category category, Restaurants restaurant);

    void deleteFood(Long id_plat) throws Exception;

    public List<plats> getRestaurantsPlats(Long id_restaurant, Category category);

    public List<plats> searchPlats(String Keyword);

    public plats findPlatsById(Long id_plat) throws Exception;

    public plats updateAvailibilityStatus(Long id_plat) throws Exception;
}
