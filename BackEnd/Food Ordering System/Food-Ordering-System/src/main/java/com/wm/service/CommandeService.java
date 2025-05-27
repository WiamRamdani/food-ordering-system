package com.wm.service;

import java.util.List;

import com.wm.model.Commande;
import com.wm.model.Admin;
import com.wm.request.CommandeRequest;

public interface CommandeService {

    public Commande createCommande(CommandeRequest commande, Admin utilisateur) throws Exception;

    public Commande updateCommande(Long id_commande, String Statut) throws Exception;

    public void cancelCommande(Long id_commande) throws Exception;

    public List<Commande> getUserOrders(Long id_user) throws Exception;

    public List<Commande> getRestaurantOrder(Long id_restaurant, String Statut) throws Exception;

    public Commande findOrderById(Long id_cmd) throws Exception;
    
}
