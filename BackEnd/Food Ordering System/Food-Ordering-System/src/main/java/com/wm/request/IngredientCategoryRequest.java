package com.wm.request;

import lombok.Data;

@Data
public class IngredientCategoryRequest {

    private String nom;
    private Long id_restaurant;
}
