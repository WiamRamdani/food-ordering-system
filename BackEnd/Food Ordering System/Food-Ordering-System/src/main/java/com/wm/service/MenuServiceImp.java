package com.wm.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.model.MenuItems;
import com.wm.model.Menu;
import com.wm.model.plats;
import com.wm.model.utilisateur;
import com.wm.repository.MenuItemsRepository;
import com.wm.repository.MenuRepository;
import com.wm.request.AddMenuItemRequest;

@Service
public class MenuServiceImp implements MenuService{

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private FoodService foodService;


    @Override
    public MenuItems addItemToMenu(AddMenuItemRequest req, String jwt) throws Exception {
        
        utilisateur utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
        
        plats plat = foodService.findPlatsById(req.getId_plat());

        Menu menu = menuRepository.findByClientId(utilisateur.getId_utilisateur());

        for(MenuItems menuItem : menu.getItems()){
            if(menuItem.getPlat().equals(plat)){
                int newQuantity=menuItem.getQuantity()+req.getQuantity();
                return updateCartItemQuantity(menuItem.getId(), newQuantity);
            }
        }
        MenuItems newMenuItem = new MenuItems();
        newMenuItem.setPlat(plat);
        newMenuItem.setMenu(menu);
        newMenuItem.setQuantity(req.getQuantity());
        newMenuItem.setPrixTotal(req.getPrixTotal()*plat.getPrix());

        MenuItems savedMenuItems=menuItemsRepository.save(newMenuItem);
        menu.getItems().add(savedMenuItems);

        return savedMenuItems;
    }

    @Override
    public MenuItems updateCartItemQuantity(Long idMenuItem, int quantity) throws Exception{
        Optional<MenuItems> opt=menuItemsRepository.findById(idMenuItem);
        if(opt.isEmpty()){
            throw new Exception("cart item not found");
        }
        MenuItems item = opt.get();
        item.setQuantity(quantity);
        item.setPrixTotal(item.getPlat().getPrix()*quantity);
        return menuItemsRepository.save(item);
    }

    @Override
    public Menu removeItemFromMenu(Long id_menu, String jwt) throws Exception {
        
        utilisateur utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
        
        Menu menu = menuRepository.findByClientId(utilisateur.getId_utilisateur());

        Optional<MenuItems> opt=menuItemsRepository.findById(id_menu);
        if(opt.isEmpty()){
            throw new Exception("cart item not found");
        }

        MenuItems item=opt.get();

        menu.getItems().remove(item);

        return menuRepository.save(menu);
    }

    @Override
    public Menu findMenuByUserId(Long id_user) throws Exception {

        Menu menu =  menuRepository.findByClientId(id_user);
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

        menu.getItems().clear();
        return menuRepository.save(menu);
    }

    @Override
    public double calculerTotalMenu(Menu menu) throws Exception {
        
        double total = 0;

        for(MenuItems menuItem : menu.getItems()){
            total += menuItem.getPlat().getPrix()*menuItem.getQuantity();
        }
        return total;

    }

}
