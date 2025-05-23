package com.wm.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantDto {

    private Long id;
    private String title;
    private List<String> images;
    private String description;
}