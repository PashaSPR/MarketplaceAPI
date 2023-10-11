package com.example.webshopdip.dtos;

import com.example.webshopdip.entities.SubcategoriesGoodsEntity;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class CategoriesGoodsDTO {

    private Long id; // Унікальний ідентифікатор категорії товару
    private String name; // Назва категорії товару

    ///////сутності що мають відношення One-to-Many з сутністю Goods
    @OneToMany(mappedBy = "categoriesGoods")
    // Зв'язок One-to-Many: Одна категорія товару може мати багато підкатегорій
    private List<SubcategoriesGoodsEntity> subcategoriesGoods = new ArrayList<>();

    public CategoriesGoodsDTO() {
    }

    public CategoriesGoodsDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
