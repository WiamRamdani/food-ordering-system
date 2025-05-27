package com.wm.service;

import com.wm.model.Adresse;
import com.wm.model.Owner;
import com.wm.model.Restaurants;
import com.wm.model.Admin;
import com.wm.repository.AddressRepository;
import com.wm.repository.AdminRepository;
import com.wm.repository.RestaurantRepository;
import com.wm.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AdminRepository userRepository;

    @Override
    public Restaurants createRestaurant(CreateRestaurantRequest req, Admin utilisateur) {
        if(!(utilisateur instanceof Owner)){
            throw new IllegalArgumentException("User must be an owner to create a restaurant.");
        }
        Owner owner =(Owner) utilisateur;
        Adresse adresse = req.getAdresse();
        adresse.setUtilisateur(utilisateur);
        Adresse savedAdresse = addressRepository.save(adresse);

        Restaurants restaurant = new Restaurants();
        restaurant.setAdresse(savedAdresse);
        restaurant.setContactInfos(req.getContactInfos());
        restaurant.setType_cuisine(req.getType_cuisine());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setNom(req.getNom());
        restaurant.setHeure_fermeture(req.getHeure_fermeture());
        restaurant.setHeure_ouverture(req.getHeure_ouverture());
        
        restaurant.setOwner(owner);// Save the owner first to get the ID
        restaurant=restaurantRepository.save(restaurant); // Save the restaurant to get its ID
        owner.setRestaurant(restaurant);
        userRepository.save(owner); // Set the restaurant in the owner
        return restaurant;
    }

    @Override
    public Restaurants updateRestaurant(Long id_restaurant, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurants restaurant = findRestaurantById(id_restaurant);

        if(restaurant.getType_cuisine()!=null){
            restaurant.setType_cuisine(updatedRestaurant.getType_cuisine());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if(restaurant.getNom()!=null){
            restaurant.setNom(updatedRestaurant.getNom());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id_restaurant) throws Exception {
        Restaurants restaurants = findRestaurantById(id_restaurant);
        restaurantRepository.delete(restaurants);
    }

    @Override
    public List<Restaurants> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurants> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurants findRestaurantById(Long id_restaurant) throws Exception {
        Optional<Restaurants> opt = restaurantRepository.findById(id_restaurant);

        if(!opt.isPresent()){
            throw new Exception("restaurant not found with id "+id_restaurant);
        }
        return opt.get();
    }

    @Override
    public Restaurants getRestaurantByUserId(Long owner_id) throws Exception {
        Restaurants restaurant = restaurantRepository.findByOwner_IdAdmin(owner_id);
        if(restaurant == null){
            throw new Exception("restaurant not found with owner id "+owner_id);
        }
        return restaurant;
    }

    @Override
    public Restaurants addToFavorites(Long id_restaurant, Admin utilisateur) throws Exception {

        Restaurants restaurant = findRestaurantById(id_restaurant);
        Restaurants dto = new Restaurants();
        dto.setImages(restaurant.getImages());
        dto.setDescription(restaurant.getDescription());
        dto.setTitle(restaurant.getNom());
        dto.setId(id_restaurant);
        dto.setOuvert(restaurant.isOuvert());
        dto.setType_cuisine(restaurant.getType_cuisine());
        dto.setHeure_fermeture(restaurant.getHeure_fermeture());
        dto.setHeure_ouverture(restaurant.getHeure_ouverture());
        dto.setAdresse(restaurant.getAdresse());
        dto.setContactInfos(restaurant.getContactInfos());

        boolean isFavorited = false;
        List<Restaurants> favorites = utilisateur.getPreferences_restau();
        for (Restaurants favorite : favorites){
            if(favorite.getId().equals(id_restaurant)){
                isFavorited= true ;
                break;
            }
            
        }

        if (isFavorited){
            favorites.removeIf(favorite -> favorite.getId().equals(id_restaurant));
        } else {
            favorites.add(dto);
        }
        userRepository.save(utilisateur);
        return dto;
    }

    @Override
    public Restaurants updateRestaurantStatus(Long id_restaurant) throws Exception {

        Restaurants restaurant = findRestaurantById(id_restaurant);
        restaurant.setOuvert(!restaurant.isOuvert());
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurants findRestaurantByNom(String nom) throws Exception {
        Optional<Restaurants> opt = restaurantRepository.findByNom(nom);
        if(!opt.isPresent()){
            throw new Exception("restaurant not found with name: "+nom);
        }
        return opt.get();
    }
}
