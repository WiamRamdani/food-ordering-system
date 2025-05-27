package com.wm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.model.Restaurants;
import com.wm.model.Admin;
import com.wm.model.ingredients;
import com.wm.model.CommandeItems;
import com.wm.model.Menu;
import com.wm.model.plats;
import com.wm.repository.CommandeItemsRepository;
import com.wm.repository.IngredientRepository;
import com.wm.repository.MenuItemsRepository;
import com.wm.repository.MenuRepository;
import com.wm.request.AddMenuItemRequest;
import com.wm.request.CreateMenuRequest;

@Service
public class MenuServiceImp implements MenuService{

    @Autowired
    private AdminService adminService;

    @Autowired
    private CommandeItemsRepository menuItemsRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private IngredientRepository ingredientRepository;


    @Override
    public CommandeItems addItemToMenu(AddMenuItemRequest req, String jwt) throws Exception {
        
        Admin utilisateur = adminService.findUtilisateurByJwtToken(jwt);
        Restaurants restaurant=restaurantService.findRestaurantByNom(req.getNom_restaurant());
        plats plat = foodService.findByNomAndRestaurantId(req.getNom_plat(),restaurant.getId());
        Menu menu = menuRepository.findByRestaurant_IdAndClient_IdAdmin(restaurant.getId(),utilisateur.getIdAdmin())
            .orElseGet(() -> {
                Menu newMenu = new Menu();
                newMenu.setRestaurant(restaurant);
                newMenu.setClient(utilisateur);
                newMenu.setCommandeItems(new ArrayList<>());
                newMenu.setTotal(0.0);
                return menuRepository.save(newMenu);
            }
        );

        for(CommandeItems menuItem : menu.getCommandeItems()){
            if(menuItem.getPlats().getId_plat() == plat.getId_plat()){
                int newQuantity=menuItem.getQuantite()+req.getQuantity();
                return updateCartItemQuantity(menuItem.getId(), newQuantity);
            }
        }
        CommandeItems newMenuItem = new CommandeItems();
        newMenuItem.setPlats(plat);
        newMenuItem.setMenu(menu);
        newMenuItem.setQuantite(req.getQuantity());
        newMenuItem.setPrixTotal(req.getQuantity()*plat.getPrix());

        if(req.getIngredients() != null && !req.getIngredients().isEmpty()){
            List<ingredients> selectedIngredients = ingredientRepository.findByNomInAndRestaurantId(req.getIngredients(), restaurant.getId());
            newMenuItem.setIngredients(selectedIngredients);
            System.out.println("Ingrédients sélectionnés : " +
        selectedIngredients.stream().map(ingredients::getNom).toList());
        }
        

        CommandeItems savedMenuItems=menuItemsRepository.save(newMenuItem);
        menu.getCommandeItems().add(savedMenuItems);
        menu.setClient(utilisateur);
        menu.setTotal(menu.getTotal() + savedMenuItems.getPrixTotal());
        menuRepository.save(menu);

        return savedMenuItems;
    }

    @Override
    public CommandeItems updateCartItemQuantity(Long idMenuItem, int quantity) throws Exception{
        Optional<CommandeItems> opt=menuItemsRepository.findById(idMenuItem);
        if(opt.isEmpty()){
            throw new Exception("cart item not found");
        }
        CommandeItems item = opt.get();
        item.setQuantite(quantity);
        item.setPrixTotal(item.getPrixTotal()*quantity);
        return menuItemsRepository.save(item);
    }

    @Override
    public Menu removeItemFromMenu(Long id_item, String jwt) throws Exception {
        
        Admin utilisateur = adminService.findUtilisateurByJwtToken(jwt);
        
        Menu menu = menuRepository.findByClientIdAdmin(utilisateur.getIdAdmin());

        Optional<CommandeItems> opt=menuItemsRepository.findById(id_item);
        if(opt.isEmpty()){
            throw new Exception("cart item not found");
        }

        CommandeItems item=opt.get();
        menu.setTotal(menu.getTotal() - item.getPrixTotal());

        menu.getCommandeItems().remove(item);

        menuItemsRepository.delete(item); 


        return menuRepository.save(menu);
    }

    @Override
    public Menu findMenuByUserId(Long id_user) throws Exception {

        Menu menu =  menuRepository.findByClientIdAdmin(id_user);
        return menu;
    }

    @Override
    public Menu findMenuById(Long id) throws Exception {
        
        Optional<Menu> opt=menuRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("cart not found with id"+ id);
        }
        return opt.get();
    }

    @Override
    public Menu clearMenu(Long id_user) throws Exception {
    
        Menu menu=findMenuByUserId(id_user);

        menu.getCommandeItems().clear();
        menu.setTotal(0.0);
        return menuRepository.save(menu);
    }

    @Override
    public double calculerTotalMenu(Menu menu) throws Exception {
        
        double total = 0;

        for(CommandeItems menuItem : menu.getCommandeItems()){
            total += menuItem.getPrixTotal()*menuItem.getQuantite();
        }
        return total;

    }

    @Override
    public Menu createMenu(CreateMenuRequest req, String jwt) throws Exception {
        
        Admin utilisateur = adminService.findUtilisateurByJwtToken(jwt);
        
        Menu menu = new Menu();
        menu.setItems(req.getItems());
        Restaurants restaurant = restaurantService.findRestaurantByNom(req.getNom_restaurant());
        menu.setRestaurant(restaurant);

        return menuRepository.save(menu);
    }

}
