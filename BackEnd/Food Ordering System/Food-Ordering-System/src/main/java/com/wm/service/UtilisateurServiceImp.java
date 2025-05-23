package com.wm.service;

import com.wm.config.JwtProvider;
import com.wm.model.utilisateur;
import com.wm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImp implements UtilisateurService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public utilisateur findUtilisateurByJwtToken(String jwt) throws Exception {
        try {System.out.println("Token reçu : " + jwt);
            String email = jwtProvider.getEmailFromToken(jwt);
            System.out.println(">>> Email extrait du token : " + email);
            
            utilisateur utilisateur = findUtilisateurByEmail(email);
            return utilisateur;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Token invalide ou utilisateur non trouvé");
        }
    }

    @Override
    public utilisateur findUtilisateurByEmail(String email) throws Exception {

        utilisateur utilisateur=userRepository.findByEmail(email);
        if(utilisateur==null){
            throw new Exception("user not found");
        }
        return utilisateur;
    }
}
