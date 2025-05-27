package com.wm.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idAdmin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Long idAdmin;

    private String fullname;

    private String telephone;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Email
    private String email;

    // Éviter le conflit avec @DiscriminatorColumn
    @Enumerated(EnumType.STRING)
    @Column(name = "role_enum", nullable = false)

    private ROLE role;

    @ManyToMany
    @JoinTable(
        name = "admin_preferences_restaurants",
        joinColumns = @JoinColumn(name = "idAdmin"),
        inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<Restaurants> preferences_restau = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilisateur")
    private List<Adresse> adresses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "utilisateur")
    private List<avis> commentaires = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonIgnore
    private List<Commande> commandes = new ArrayList<>();
}
