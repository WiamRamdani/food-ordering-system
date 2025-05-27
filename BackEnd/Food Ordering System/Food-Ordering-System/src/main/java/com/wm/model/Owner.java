package com.wm.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("RESTAURANT_OWNER")
public class Owner extends Admin {

    @OneToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurants restaurant;

    {
        // bloc d'initialisation pour forcer le rôle
        this.setRole(ROLE.RESTAURANT_OWNER);
    }
}
