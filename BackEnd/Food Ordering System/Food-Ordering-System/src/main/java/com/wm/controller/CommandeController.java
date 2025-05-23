package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.model.Commande;
import com.wm.model.utilisateur;
import com.wm.request.CommandeRequest;
import com.wm.service.CommandeService;
import com.wm.service.UtilisateurService;

@RestController
@RequestMapping("/api")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/order")
    public ResponseEntity<Commande> createCommande(@RequestBody CommandeRequest req,
                                                   @RequestHeader("Authorization" )String jwt) throws Exception{
        utilisateur utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
        Commande commande = commandeService.createCommande(req, utilisateur);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Commande>> getOrderHistory(@RequestHeader("Authorization" )String jwt) throws Exception{
        
        utilisateur utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
        List<Commande> commande = commandeService.getUserOrders(utilisateur.getId_utilisateur());
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }
}
