package com.wm.controller;
import com.wm.model.Owner;
import com.wm.model.ROLE;
import com.wm.model.Admin;
import com.wm.config.JwtProvider;
import com.wm.repository.AdminRepository;
import com.wm.request.LoginRequest;
import com.wm.response.AuthResponse;
import com.wm.service.AdminDetailsService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AdminDetailsService adminDetailsService;


    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<AuthResponse> createAdminHandler(@Valid @RequestBody Admin admin) {
    if(adminRepository.existsByEmail(admin.getEmail())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already used");
    }
    Admin createdUser;
    
    if (admin.getRole() == ROLE.RESTAURANT_OWNER) {
        Owner owner = new Owner();
        owner.setRole(ROLE.RESTAURANT_OWNER); // important pour le discriminant
        owner.setEmail(admin.getEmail());
        owner.setFullname(admin.getFullname());
        owner.setTelephone(admin.getTelephone());
        owner.setPassword(passwordEncoder.encode(admin.getPassword()));
        createdUser = owner;
    } else {
        Admin newAdmin = new Admin();
        newAdmin.setRole(ROLE.ADMIN); // important pour le discriminant
        newAdmin.setEmail(admin.getEmail());
        newAdmin.setFullname(admin.getFullname());
        newAdmin.setTelephone(admin.getTelephone());
        newAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
        createdUser = newAdmin;
    }

    Admin savedAdmin = adminRepository.save(createdUser);

    UserDetails adminDetails = adminDetailsService.loadUserByUsername(savedAdmin.getEmail());
    Authentication authentication = new UsernamePasswordAuthenticationToken(adminDetails,null, adminDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt=jwtProvider.generateToken(authentication);
    AuthResponse authResponse = new AuthResponse();
    authResponse.setJwt(jwt);
    authResponse.setMessage("Register successful");
    String role = authentication.getAuthorities().stream()
    .findFirst()
    .map(GrantedAuthority::getAuthority)
    .map(r -> r.replace("ROLE_", "").toUpperCase()) 
    .orElseThrow(() -> new IllegalStateException("User has no roles"));

    authResponse.setRole(ROLE.valueOf(role));

    return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
}
    @PostMapping("/signin")
public ResponseEntity<AuthResponse> signin(@Valid @RequestBody LoginRequest req) {
    Authentication authentication = authenticate(req.getEmail(), req.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    String role = authentication.getAuthorities().stream()
        .findFirst()
        .map(GrantedAuthority::getAuthority)
        .map(r -> r.replace("ROLE_", "")) 
        .orElseThrow(() -> new IllegalStateException("User has no roles"));
    
    String jwt = jwtProvider.generateToken(authentication);

    AuthResponse authResponse = new AuthResponse();
    authResponse.setJwt(jwt);
    authResponse.setMessage("Login successful");
    authResponse.setRole(ROLE.valueOf(role));

    return ResponseEntity.ok(authResponse);
}
    private UsernamePasswordAuthenticationToken authenticate(String email, String password) {
        UserDetails adminDetails = adminDetailsService.loadUserByUsername(email);
        if(adminDetails==null){
            throw new BadCredentialsException("Invalid username...");
        }
        if(!passwordEncoder.matches(password, adminDetails.getPassword())){
            throw new BadCredentialsException("Invalid password...");
        }
        return new UsernamePasswordAuthenticationToken(adminDetails,null,adminDetails.getAuthorities());


    }

}