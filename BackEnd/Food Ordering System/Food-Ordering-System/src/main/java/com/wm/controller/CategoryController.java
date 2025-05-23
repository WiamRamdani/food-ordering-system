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

import com.wm.model.Category;
import com.wm.model.utilisateur;
import com.wm.service.CategoryService;
import com.wm.service.UtilisateurService;

@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                  @RequestHeader("Authorization") String jwt) throws Exception{

        utilisateur utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);

        Category createdCategory = categoryService.createCategory(category.getNom(), utilisateur.getId_utilisateur());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/restaurant")
    public ResponseEntity<List<Category>> getRestaurantCategory(@RequestHeader("Authorization") String jwt) throws Exception{

        utilisateur utilisateur = utilisateurService.findUtilisateurByJwtToken(jwt);

        List<Category> categories = categoryService.findCategoryByRestaurantId(utilisateur.getId_utilisateur());

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
