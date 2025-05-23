package com.wm.service;

import com.wm.model.MenuItems;
import com.wm.model.Menu;
import com.wm.request.AddMenuItemRequest;

public interface MenuService {

    public  MenuItems  addItemToMenu(AddMenuItemRequest req, String jwt) throws Exception;

    public MenuItems updateCartItemQuantity(Long idMenuItem, int quantity) throws Exception;

    public Menu removeItemFromMenu(Long id_menuItem, String jwt) throws Exception;

    public Menu findMenuById(Long id) throws Exception;

    public Menu findMenuByUserId(Long id_user) throws Exception;

    public Menu clearMenu(Long id_user) throws Exception;

    public double calculerTotalMenu(Menu menu) throws Exception;
}
