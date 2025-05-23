package com.wm.model;
import jakarta.persistence.*;

@Entity
@Table(name = "avis")
public class avis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_avis;

    private float note;
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private utilisateur utilisateur;

}
