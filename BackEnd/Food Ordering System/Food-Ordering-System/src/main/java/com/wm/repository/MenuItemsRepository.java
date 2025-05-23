package com.wm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.model.MenuItems;

public interface MenuItemsRepository extends JpaRepository<MenuItems, Long>{

}
