package com.wm.request;


import com.wm.model.Restaurants;

import lombok.Data;

@Data
public class AddMenuItemRequest {

    private Long id_menu;

    private Restaurants restaurant;

    private Long id_plat;

    private int quantity;

    private Long prixTotal;

}
