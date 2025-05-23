package com.wm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_menu;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurants restaurant;

    @OneToOne
    @JoinColumn(name = "id_utilisateur")
    private utilisateur client;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItems> items= new ArrayList<>();

    private double total;
}
