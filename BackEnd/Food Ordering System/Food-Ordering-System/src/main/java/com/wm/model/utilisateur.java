package com.wm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;


import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_utilisateur;

    private String fullname;

    @Email
    private String email;

    private String telephone;

    private String adresse;

    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private ROLE role=ROLE.CUSTOMER;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Commande> commandes =new ArrayList<>();

    
    // @ManyToMany
    // @JoinTable(
    // name = "user_restaurants",
    // joinColumns = @JoinColumn(name = "id_utilisateur"),
    // inverseJoinColumns = @JoinColumn(name = "id"))
    // private List<restaurants> preferences_restau = new ArrayList<>();

    @OneToMany
    private List<Restaurants> preferences_restau = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "utilisateur")
    private List<Adresse> adresses=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy = "utilisateur")
    private List<avis> commentaires=new ArrayList<>();
}