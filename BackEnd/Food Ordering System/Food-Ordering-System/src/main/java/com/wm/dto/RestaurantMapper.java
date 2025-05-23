package com.wm.dto;

import com.wm.model.Restaurants;

public class RestaurantMapper {

    public static RestaurantDto toDto(Restaurants entity) {
        RestaurantDto dto = new RestaurantDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getNom());
        dto.setImages(entity.getImages());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static Restaurants toEntity(RestaurantDto dto) {
        Restaurants entity = new Restaurants();
        entity.setId(dto.getId());
        entity.setNom(dto.getTitle());
        entity.setImages(dto.getImages());
        entity.setDescription(dto.getDescription());
        return entity;
    }

}
