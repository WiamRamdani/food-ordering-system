package com.wm.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Commandes")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_commande;


    @ManyToOne
    private Admin client;

    @JsonIgnore
    @ManyToOne
    private Restaurants restaurant;

    @ManyToOne
    private livreur livreur;

    private double montant_total;
    private String statut;
    private Date créée_a;
    private Date heure_réelle;
    private Date heure_estimée;

    @ManyToOne
    private Adresse adresse_livraison;


    @OneToMany
    private List<CommandeItems> items;

}
