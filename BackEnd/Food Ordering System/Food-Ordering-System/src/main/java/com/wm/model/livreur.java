package com.wm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class livreur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_livreur;

    private String nom;
    private String prenom;
    private String matricule_vehicule;
    private boolean disponible;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Commande> commandes=new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurants restaurant;
}
