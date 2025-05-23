package com.wm.request;

import lombok.Data;

@Data
public class IngredientRequest {

    private String nom;

    private Long id_restau;

    private Long id_category;
}
