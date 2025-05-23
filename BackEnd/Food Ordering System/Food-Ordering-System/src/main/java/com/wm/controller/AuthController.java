package com.wm.controller;
import com.wm.model.ROLE;
import com.wm.model.admin;
import com.wm.config.JwtProvider;
import com.wm.repository.AdminRepository;
import com.wm.request.LoginRequest;
import com.wm.response.AuthResponse;
import com.wm.service.AdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.security.authentication.AuthenticationManager;


import java.util.Collection;

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
    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createAdminHandler(@RequestBody admin admin) throws Exception {

        admin isEmailExist = adminRepository.findByEmail(admin.getEmail());
        if(isEmailExist != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already used with another account");
        }
        admin createdAdmin = new admin();
        createdAdmin.setEmail(admin.getEmail());
        createdAdmin.setFullname(admin.getFullname());
        // createdAdmin.setTelephone(admin.getTelephone());
        createdAdmin.setRole(admin.getRole());
        createdAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));

        admin savedAdmin = adminRepository.save(createdAdmin);

        Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(admin.getEmail(), admin.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Authentication authentication = new UsernamePasswordAuthenticationToken(admin.getEmail(), admin.getPassword());
        // SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register successful");
        authResponse.setRole(savedAdmin.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req) {

        String email = req.getEmail();
        String password = req.getPassword();

        Authentication authentication = authenticate(email, password);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login successful");

        authResponse.setRole(ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

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
