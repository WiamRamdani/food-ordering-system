package com.wm.service;

import com.wm.model.plats;
import com.wm.model.CommandeItems;
import com.wm.model.Menu;
import com.wm.request.AddMenuItemRequest;
import com.wm.request.CreateMenuRequest;

public interface MenuService {

    public Menu createMenu(CreateMenuRequest req,String jwt) throws Exception;

    public  CommandeItems  addItemToMenu(AddMenuItemRequest req, String jwt) throws Exception;

    public CommandeItems updateCartItemQuantity(Long idPlat, int quantity) throws Exception;

    public Menu removeItemFromMenu(Long id_item, String jwt) throws Exception;

    public Menu findMenuById(Long id) throws Exception;

    public Menu findMenuByUserId(Long id_user) throws Exception;

    public Menu clearMenu(Long id_user) throws Exception;

    public double calculerTotalMenu(Menu menu) throws Exception;
}
