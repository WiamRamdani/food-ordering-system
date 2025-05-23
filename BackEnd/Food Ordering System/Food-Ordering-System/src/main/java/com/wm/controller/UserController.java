package com.wm.controller;

import com.wm.model.utilisateur;
import com.wm.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UtilisateurService utilisateurService;
    @GetMapping("/profile")
    public ResponseEntity<?> findUtilisateurByJwtToken(@RequestHeader("Authorization") String authorizationHeader) {
    try {
        String token = authorizationHeader.replace("Bearer ", "").trim();
        utilisateur utilisateur = utilisateurService.findUtilisateurByJwtToken(token);
        return new ResponseEntity<>(utilisateur, HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Erreur : " + e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}}

