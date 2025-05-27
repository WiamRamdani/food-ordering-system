package com.wm.service;

import com.wm.model.Restaurants;
import com.wm.model.Admin;
import com.wm.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurants createRestaurant(CreateRestaurantRequest req, Admin utilisateur);

    public Restaurants updateRestaurant(Long id_restaurant, CreateRestaurantRequest updatedRestaurant) throws Exception;

    public void deleteRestaurant(Long id_restaurant) throws Exception;

    public List<Restaurants> getAllRestaurants();

    public List<Restaurants> searchRestaurant(String keyword);

    public Restaurants findRestaurantById(Long id_restaurant) throws Exception;

    public Restaurants getRestaurantByUserId(Long userId) throws Exception;

    public Restaurants addToFavorites(Long id_restaurant, Admin utilisateur) throws Exception;

    public Restaurants updateRestaurantStatus(Long id_restaurant) throws Exception;

    public Restaurants findRestaurantByNom(String nom) throws Exception;




}
