package com.wm.controller;

import com.wm.exception.AdminNotFoundException;
import com.wm.model.Admin;
import com.wm.service.AdminService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/profile")
    public ResponseEntity<?> getAdminProfile(@RequestHeader("Authorization") String authHeader)  {
        try {
            String jwt = authHeader.substring(7); // Supprime "Bearer "
            log.debug("Tentative d'accès au profil avec JWT");
            Admin admin = adminService.findUtilisateurByJwtToken(jwt);
            return ResponseEntity.ok(admin);
            
        } catch (AdminNotFoundException e) {
            log.warn("Admin non trouvé : {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            
        } catch (Exception e) {
            log.error("Erreur technique lors de l'accès au profil", e);
            return ResponseEntity.internalServerError()
                   .body("Une erreur technique est survenue");
        }
}}

