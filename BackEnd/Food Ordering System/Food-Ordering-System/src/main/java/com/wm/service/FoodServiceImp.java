package com.wm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wm.model.Category;
import com.wm.model.plats;
import com.wm.model.Restaurants;
import com.wm.repository.FoodRepository;
import com.wm.request.CreateFoodRequest;



@Service
public class FoodServiceImp implements FoodService{

    private FoodRepository foodRepository;

    @Override
    public plats createPlats(CreateFoodRequest req, Category category, Restaurants restaurant) {
        plats plat = new plats();
        plat.setCategorie(category);;
        plat.setRestaurant(restaurant);
        plat.setDescription(req.getDescription());
        plat.setImage(req.getImage());
        plat.setNom(req.getNom());
        plat.setPrix(req.getPrix());
        plat.setQuantite(req.getQuantite());
        plat.setIngredients(req.getIngredients());

        plats savedFood = foodRepository.save(plat);
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
    public List<plats> getRestaurantsPlats(Long id_restaurant, Category category) {
        
        List<plats> plats = foodRepository.findByRestaurantId(id_restaurant);
        if(category!=null && !category.equals("")){
            plats = filterByCategory(plats, category);
        }
        return plats;
    }

    private List<plats> filterByCategory(List<plats> plats, Category category) {
        return plats.stream().filter(plat -> {
            if(plat.getCategorie()!=null){
                return plat.getCategorie().equals(category);
            }
            return false;
        }).collect(Collectors.toList());
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

}
