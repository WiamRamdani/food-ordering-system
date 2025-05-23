package com.wm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.model.Adresse;
import com.wm.model.Commande;
import com.wm.model.CommandeItems;
import com.wm.model.MenuItems;
import com.wm.model.Menu;
import com.wm.model.Restaurants;
import com.wm.model.utilisateur;
import com.wm.repository.AddressRepository;
import com.wm.repository.CommandeItemsRepository;
import com.wm.repository.CommandeRepository;
import com.wm.repository.UserRepository;
import com.wm.request.CommandeRequest;

@Service
public class CommandeServiceImp  implements CommandeService{

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private CommandeItemsRepository commandeItemsRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private MenuService menuService;

    @Override
    public Commande createCommande(CommandeRequest commande, utilisateur utilisateur) throws Exception {
       
        Adresse adresseLivraison = commande.getAdresseLivraison();

        Adresse savedAddress = addressRepository.save(adresseLivraison);

        if(!utilisateur.getAdresses().contains(savedAddress)){
            utilisateur.getAdresses().add(savedAddress);
            userRepository.save(utilisateur);
        }

        Restaurants restaurant = restaurantService.findRestaurantById(commande.getId_restaurant());

        Commande createdCommande = new Commande();
        createdCommande.setClient(utilisateur);
        createdCommande.setCréée_a(new Date());
        createdCommande.setStatut("PENDING");
        createdCommande.setAdresse_livraison(savedAddress);
        createdCommande.setRestaurant(restaurant);

        Menu menu = menuService.findMenuByUserId(utilisateur.getId_utilisateur());

        List<CommandeItems> cmdItems= new ArrayList<>();

        for(MenuItems items : menu.getItems()){
            CommandeItems item_cmd = new CommandeItems();
            item_cmd.setPlats(items.getPlat());
            item_cmd.setQuantite(items.getQuantity());
            item_cmd.setPrixTotal(items.getPrixTotal());

            CommandeItems savedCommandeItems = commandeItemsRepository.save(item_cmd);
            cmdItems.add(savedCommandeItems);

        }
        double prixTotal = menuService.calculerTotalMenu(menu);
        createdCommande.setItems(cmdItems);
        createdCommande.setMontant_total(prixTotal);

        Commande savedCommande = commandeRepository.save(createdCommande);
        restaurant.getCommandes().add(savedCommande);

        return createdCommande;
    }

    @Override
    public Commande updateCommande(Long id_commande, String Statut) throws Exception  {
        
        Commande commande = findOrderById(id_commande);
        if(Statut.equals("OUT_FOR_DELIVERY") || 
                  Statut.equals("DELIVERED") || 
                  Statut.equals("COMPLETED") ||
                  Statut.equals("PENDING")){
            
            commande.setStatut(Statut);
            return commandeRepository.save(commande);
        }
        throw new Exception("Please select a valid order statuts");
    }

    @Override
    public void cancelCommande(Long id_commande) throws Exception {
    
        Commande commande = findOrderById(id_commande);
        commandeRepository.deleteById(id_commande);
    }

    @Override
    public List<Commande> getUserOrders(Long id_user) throws Exception {
        return commandeRepository.findByClientId(id_user);
    }

    @Override
    public List<Commande> getRestaurantOrder(Long id_restaurant, String Statut) throws Exception {
        List<Commande> commandes = commandeRepository.findByRestaurantId(id_restaurant);
        if(Statut!=null){
            commandes = commandes.stream().filter(commande ->
                        commande.getStatut().equals(Statut)).collect(Collectors.toList());                           
        }
        return commandes;
    }

    @Override
    public Commande findOrderById(Long id_cmd) throws Exception {
        Optional<Commande> opt = commandeRepository.findById(id_cmd);
        if(opt.isEmpty()){
            throw new Exception("order not found");
        }
        return opt.get();
    }

}
