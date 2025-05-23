package com.wm.service;

import com.wm.model.utilisateur;

public interface UtilisateurService {

    public utilisateur findUtilisateurByJwtToken(String jwt) throws Exception;

    public utilisateur findUtilisateurByEmail(String email) throws Exception;

}


