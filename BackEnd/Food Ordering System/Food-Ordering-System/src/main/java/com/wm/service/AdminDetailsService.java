package com.wm.service;

import com.wm.model.ROLE;
import com.wm.model.Admin;
import com.wm.repository.AdminRepository;
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
public class AdminDetailsService implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Admin admin =adminRepository.findByEmail(username);
        if(admin == null){
            throw new UsernameNotFoundException("admin not found with this email"+username);
        }
        ROLE role=admin.getRole();
        List<GrantedAuthority> roles=new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));;

        return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(), roles);
    }

}
