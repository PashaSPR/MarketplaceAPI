package com.example.webshopdip.dtos;

import java.util.ArrayList;
import java.util.List;

public class SubcategoriesGoodsDTO {
    private Long id; // Унікальний ідентифікатор Підкатегорії Товарів
    private String name; // Назва Підкатегорії Товарів

    // Зв'язок Many-to-One: Багато Підкатегорій Товарів може стосуватися однієї Категорії Товарів
    private Long categoriesGoodsId;

    /////////сутності що мають відношення One-to-Many з сутністю Goods
    private List<PropertiesNameGoodsDTO> propertiesNameGoodsDTOS = new ArrayList<>();

    public SubcategoriesGoodsDTO() {
    }

    public SubcategoriesGoodsDTO(Long id, String name, Long categoriesGoodsId) {
        this.id = id;
        this.name = name;
        this.categoriesGoodsId = categoriesGoodsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoriesGoodsId() {
        return categoriesGoodsId;
    }

    public void setCategoriesGoodsId(Long categoriesGoodsId) {
        this.categoriesGoodsId = categoriesGoodsId;
    }

    public List<PropertiesNameGoodsDTO> getPropertiesNameGoodsDTOS() {
        return propertiesNameGoodsDTOS;
    }

    public void setPropertiesNameGoodsDTOS(List<PropertiesNameGoodsDTO> propertiesNameGoodsDTOS) {
        this.propertiesNameGoodsDTOS = propertiesNameGoodsDTOS;
    }
}
