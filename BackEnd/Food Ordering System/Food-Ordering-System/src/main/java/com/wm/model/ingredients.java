package com.wm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_ingredient;

    @ManyToOne
    private plats plat;

    @ManyToOne
    private IngredientCategory category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_restaurant")
    private Restaurants restaurant;

    private boolean enStock=true;

    private String nom;
}
