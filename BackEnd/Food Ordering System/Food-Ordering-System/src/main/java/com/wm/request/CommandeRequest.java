package com.wm.request;

import com.wm.model.Adresse;

import lombok.Data;

@Data
public class CommandeRequest {
    private Long id_restaurant;

    private Adresse adresseLivraison;
}
