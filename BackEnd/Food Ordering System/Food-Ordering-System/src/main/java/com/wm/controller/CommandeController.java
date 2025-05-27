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
import com.wm.model.Admin;
import com.wm.request.CommandeRequest;
import com.wm.service.AdminService;
import com.wm.service.CommandeService;

@RestController
@RequestMapping("/api")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private AdminService utilisateurService;

    @PostMapping("/order")
    public ResponseEntity<Commande> createCommande(@RequestBody CommandeRequest req,
                                                   @RequestHeader("Authorization" )String jwt) throws Exception{
        Admin utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
        Commande commande = commandeService.createCommande(req, utilisateur);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Commande>> getOrderHistory(@RequestHeader("Authorization" )String jwt) throws Exception{
        
        Admin utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
        List<Commande> commande = commandeService.getUserOrders(utilisateur.getIdAdmin());
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }
}
