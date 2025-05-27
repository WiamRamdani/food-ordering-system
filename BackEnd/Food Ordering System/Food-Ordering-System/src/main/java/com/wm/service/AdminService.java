package com.wm.service;

import com.wm.model.Admin;

public interface AdminService {

    public Admin findUtilisateurByJwtToken(String jwt) throws Exception;

    public Admin findUtilisateurByEmail(String email) throws Exception;


}


