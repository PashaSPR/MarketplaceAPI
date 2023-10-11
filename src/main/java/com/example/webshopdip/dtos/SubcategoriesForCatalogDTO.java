package com.example.webshopdip.dtos;

import java.util.ArrayList;
import java.util.List;

public class SubcategoriesForCatalogDTO {
    private Long id; // Унікальний ідентифікатор Підкатегорії Товарів
    private String name; // Назва Підкатегорії Товарів
    private List<GoodsForCatalogDTO> goods = new ArrayList<>();

    public SubcategoriesForCatalogDTO() {
    }

    public SubcategoriesForCatalogDTO(Long id, String name, List<GoodsForCatalogDTO> goods) {
        this.id = id;
        this.name = name;
        this.goods = goods;
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

    public List<GoodsForCatalogDTO> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsForCatalogDTO> goods) {
        this.goods = goods;
    }
}
