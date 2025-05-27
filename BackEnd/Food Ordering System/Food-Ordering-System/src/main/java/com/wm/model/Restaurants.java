package com.wm.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Restaurants {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    private String ville;
    private String description;
    private String login;
    private String title;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "telephone", insertable = false, updatable = false)
    private String telephone;
    private float note;
    private boolean ouvert;
    private Date heure_ouverture;
    private Date heure_fermeture;
    private String Type_cuisine;


    @OneToOne(mappedBy = "restaurant")
    private livreur livreur;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    
    @OneToOne
    private Adresse adresse;

    @Embedded
    private contact_infos contactInfos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commande> commandes=new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    private List<String> images;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<plats> plats=new ArrayList<>();

    @OneToMany
    private List<Menu> menus=new ArrayList<>();


    @ManyToMany(mappedBy = "preferences_restau")
    @JsonIgnore
    private List<Admin> adminsWhoPrefer = new ArrayList<>();

    
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

}