package com.wm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class CommandeItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantite;

    private double prixTotal;

    @ManyToMany
    @JoinTable(
    name = "commande_items_ingredients",
    joinColumns = @JoinColumn(name = "commande_items_id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id")
)
    @JsonIgnoreProperties({"commandeItems", "plat", "restaurant", "category"})
    private List<ingredients> ingredients;

    @ManyToOne
    private plats plats;

    


    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu menu;

}
