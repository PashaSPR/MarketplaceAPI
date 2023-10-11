package com.example.webshopdip.dtos;

import java.util.List;

public class GoodsGetAllByCategoryDTO {
    private Long id; // Унікальний ідентифікатор Товару
    private String name; // Назва Товару
    private String short_discription; // Короткий опис Товару
    private SubcategoriesGoodsDTO subcategoriesGoodsDTO;// Підкатегорії Товару
    private List<PhotosGoodsDTO> photosGoodsDTOS;
    private List<PropertiesGoodsDTO> propertiesGoodsDTOS;
}
