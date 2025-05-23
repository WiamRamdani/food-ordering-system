package com.wm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class plats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_plat;

    private String nom;

    private String description;

    private int quantite;

    private double prix;

    private float note;

    private Date date_creation;

    @ManyToOne
    private Category categorie;

    @Column(length=1000)
    @ElementCollection
    private List<String> image;

    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurants restaurant;

    @ManyToMany
    private List<ingredients> ingredients = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu menu;
}

