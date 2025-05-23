package com.wm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wm.model.Commande;
import com.wm.model.utilisateur;
import com.wm.service.CommandeService;
import com.wm.service.UtilisateurService;

@RestController
@RequestMapping("api/admin")
public class AdminOrderController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private UtilisateurService utilisateurService;


    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<List<Commande>> getOrderHistory(
        @PathVariable Long id,
        @RequestParam(required = false) String statutCmd,
        @RequestHeader("Authorization" )String jwt) throws Exception{
        
        utilisateur utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
        List<Commande> commande = commandeService.getRestaurantOrder(id, statutCmd);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    @GetMapping("/order/{id}/{statutCmd}")
    public ResponseEntity<Commande> updateOrderStatuts(
        @PathVariable Long id,
        @RequestParam(required = false) String statutCmd,
        @RequestHeader("Authorization" )String jwt) throws Exception{
        
        utilisateur utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
        Commande commande = commandeService.updateCommande(id, statutCmd);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

}
