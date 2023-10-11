package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


/**
 * Клас, що представляє сутність для зберігання зв'язків
 * між категоріями та типами властивостей. Кожен зв'язок буде
 * вказувати, які типи властивостей доступні для кожної категорії
 * Дата створення: 31.07.2023
 */
@Entity
@Table(name = "categoriesproperties")
public class CategoriesPropertiesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор категорії товару
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "subcategoriesGoods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато зв'язків може стосуватися одній Субкатегорії
    private SubcategoriesGoodsEntity subcategoriesGoods;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "propertiesNameGoods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато зв'язків може стосуватися одній Субкатегорії
    private PropertiesNameGoodsEntity propertiesNameGoods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubcategoriesGoodsEntity getSubcategoriesGoods() {
        return subcategoriesGoods;
    }

    public void setSubcategoriesGoods(SubcategoriesGoodsEntity subcategoriesGoods) {
        this.subcategoriesGoods = subcategoriesGoods;
    }

    public PropertiesNameGoodsEntity getPropertiesNameGoods() {
        return propertiesNameGoods;
    }

    public void setPropertiesNameGoods(PropertiesNameGoodsEntity propertiesNameGoods) {
        this.propertiesNameGoods = propertiesNameGoods;
    }
}
