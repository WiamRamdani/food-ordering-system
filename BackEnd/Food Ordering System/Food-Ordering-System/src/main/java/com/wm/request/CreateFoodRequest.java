package com.wm.request;

import java.util.Date;
import java.util.List;

import com.wm.model.Category;
import com.wm.model.ingredients;
import com.wm.model.Restaurants;

import lombok.Data;

@Data
public class CreateFoodRequest {
    private String nom;

    private String description;

    private int quantite;

    private double prix;

    private float note;

    private Date date_creation;

    private List<String> image;

    private boolean disponible;

    private Long id_restaurant;

    private List<ingredients> ingredients;

    private Category categorie;

    private Restaurants restaurant;

}
