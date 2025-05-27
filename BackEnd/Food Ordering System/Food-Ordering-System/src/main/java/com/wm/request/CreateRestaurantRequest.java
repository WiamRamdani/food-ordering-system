package com.wm.request;

import com.wm.model.Adresse;
import com.wm.model.contact_infos;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Adresse adresse;
    private long id;
    private String nom;
    private String ville;
    private String description;
    private String telephone;
    private Date heure_ouverture;
    private Date heure_fermeture;
    private String Type_cuisine;
    private contact_infos contactInfos;
    private List<String> images;

}
