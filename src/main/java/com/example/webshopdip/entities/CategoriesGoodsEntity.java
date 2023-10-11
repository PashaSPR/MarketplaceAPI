package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Категорії Товарів".
 * Характеризує загальні категорії товарів.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "categoriesgoods")

public class CategoriesGoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор категорії товару
    private String name; // Назва категорії товару

    /////////сутності що мають відношення One-to-Many з сутністю Goods

    @OneToMany(mappedBy = "categoriesGoods")
    @JsonManagedReference
    // Зв'язок One-to-Many: Одна категорія товару може мати багато підкатегорій
    private List<SubcategoriesGoodsEntity> subcategoriesGoods = new ArrayList<>();

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

    public List<SubcategoriesGoodsEntity> getSubcategoriesGoods() {
        return subcategoriesGoods;
    }

    public void setSubcategoriesGoods(List<SubcategoriesGoodsEntity> subcategoriesGoods) {
        this.subcategoriesGoods = subcategoriesGoods;
    }
}
