package com.wm.service;

import com.wm.config.JwtProvider;
import com.wm.exception.AdminNotFoundException;
import com.wm.model.Admin;
import com.wm.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImp implements AdminService{

    @Autowired
    private AdminRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public Admin findUtilisateurByJwtToken(String jwt) throws Exception {
        
        String email = jwtProvider.getEmailFromToken(jwt);
        System.out.println("Email extrait du token :  " + email);
        Admin user = findUtilisateurByEmail(email);
        if (user == null) {
            throw new AdminNotFoundException("User not found with this email: " + email);
        }
        System.out.println("Admin récupéré : id = " + user.getIdAdmin() + ", email = " + user.getEmail());
        return user;
    }

    @Override
    public Admin findUtilisateurByEmail(String email) throws Exception {

        Admin user=userRepository.findByEmail(email);
        if(user==null){
            throw new AdminNotFoundException("User not found with this email: " + email);
        }
        return user;
    }

    }
