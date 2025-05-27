package com.wm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.model.Admin;
import com.wm.model.plats;
import com.wm.model.CommandeItems;
import com.wm.model.Menu;
import com.wm.request.AddMenuItemRequest;
import com.wm.request.CreateMenuRequest;
import com.wm.request.updateMenuItemRequest;
import com.wm.service.AdminService;
import com.wm.service.MenuService;

@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private AdminService utilisateurService;

    @Autowired
    private MenuService menuService;

    @PostMapping("/menu/create")
    public ResponseEntity<Menu> createMenu(@RequestBody CreateMenuRequest req,
                                           @RequestHeader("Authorization" )String jwt) throws Exception{
        Admin utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
        Menu menu = menuService.createMenu(req, jwt);
        return new ResponseEntity<>(menu, HttpStatus.CREATED);
    }

    @PutMapping("/menu/add")
    public ResponseEntity<CommandeItems> addItemToMenu(@RequestBody AddMenuItemRequest req,
                                                   @RequestHeader("Authorization" )String jwt) throws Exception{
        CommandeItems menuItems = menuService.addItemToMenu(req, jwt);
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @PutMapping("/menu_item/update")
    public ResponseEntity<CommandeItems> updateMenuItemQuantity(@RequestBody updateMenuItemRequest req,
                                                           @RequestHeader("Authorization" )String jwt) throws Exception{
        CommandeItems menuItems = menuService.updateCartItemQuantity(req.getIdMenuItem(), req.getQuantity());
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    @PutMapping("/menu_item/{id}/remove")
    public ResponseEntity<Menu> removeMenuItem(@PathVariable Long id,
                                               @RequestHeader("Authorization" )String jwt) throws Exception{
        Menu menu = menuService.removeItemFromMenu(id, jwt);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @PutMapping("/menu/clear")
    public ResponseEntity<Menu> clearMenu(@RequestHeader("Authorization" )String jwt) throws Exception{
        Admin utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
    
        Menu menu = menuService.clearMenu(utilisateur.getIdAdmin());
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @GetMapping("/menu")
    public ResponseEntity<Menu> findUserMenu(@RequestHeader("Authorization" )String jwt) throws Exception{
        Admin utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);
        Menu menu = menuService.findMenuByUserId(utilisateur.getIdAdmin());
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }
}
