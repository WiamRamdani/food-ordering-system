package com.wm.service;

import com.wm.model.ROLE;
import com.wm.model.utilisateur;
import com.wm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        utilisateur utilisateur =userRepository.findByEmail(username);
        if(utilisateur == null){
            throw new UsernameNotFoundException("user not found with this email"+username);
        }
        ROLE role=utilisateur.getRole();
        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));;

        return new org.springframework.security.core.userdetails.User(utilisateur.getEmail(), utilisateur.getPassword(), authorities);
    }

}
