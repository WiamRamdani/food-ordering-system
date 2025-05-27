package com.wm.request;


import java.util.List;

import com.wm.model.Restaurants;

import lombok.Data;

@Data
public class AddMenuItemRequest {


    private String nom_restaurant;

    private String nom_plat;


    private int quantity;


    private List<String> ingredients;

}
