package com.wm.model;
import jakarta.persistence.*;

@Entity

public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_adresse;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private utilisateur utilisateur;
}
