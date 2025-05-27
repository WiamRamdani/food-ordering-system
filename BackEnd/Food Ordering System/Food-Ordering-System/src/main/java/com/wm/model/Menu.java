package com.wm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id_menu", "commandesItems", "client", "restaurant"})
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_menu;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurants restaurant;

    @OneToOne
    private Admin client;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<plats> items;

    private double total;

    @OneToMany(mappedBy = "menu",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CommandeItems> commandeItems ;
}
