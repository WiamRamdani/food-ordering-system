package com.wm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    
    private String nom;
    
    @JsonIgnore
    @ManyToOne
    private plats plat;

    @ManyToOne
    private IngredientCategory category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_restaurant")
    private Restaurants restaurant;

    private boolean enStock=true;

    @ManyToMany(mappedBy = "ingredients")
    private List<CommandeItems> commandeItems;

    
}
