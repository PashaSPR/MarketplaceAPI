package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Підкатегорії Товарів".
 * Характеризує Підкатегорії товарів, які належать до певних Категорій Товарів.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "subcategoriesgoods")
public class SubcategoriesGoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Підкатегорії Товарів
    private String name; // Назва Підкатегорії Товарів
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "categoriesGoods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Підкатегорій Товарів може стосуватися однієї Категорії Товарів
    private CategoriesGoodsEntity categoriesGoods;

    /////////сутності що мають відношення One-to-Many з сутністю Goods

    @OneToMany(mappedBy = "subcategoriesGoods")
    @JsonManagedReference
    // Зв'язок One-to-Many: Одна Категорія може відноситися до багатьох Товарів
    private List<GoodsEntity> goods = new ArrayList<>();
    @OneToMany(mappedBy = "subcategoriesGoods", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    // Зв'язок One-to-Many: Одна Категорія може відноситися до багатьох Зв'язків
    // між категоріями та типами властивостей
    private List<CategoriesPropertiesEntity> categoriesProperties = new ArrayList<>();

    @ManyToMany
    // Зв'язок Many-to-Many: Багато Категорії може відноситися до багатьох Властивостей товару
    @JoinTable(
            name = "subcategories_properties",
            joinColumns = @JoinColumn(name = "subcategories_id"),
            inverseJoinColumns = @JoinColumn(name = "properties_id")
    )
    private List<PropertiesNameGoodsEntity> propertiesNameGoods = new ArrayList<>();

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

    public CategoriesGoodsEntity getCategoriesGoods() {
        return categoriesGoods;
    }

    public void setCategoriesGoods(CategoriesGoodsEntity categoriesGoods) {
        this.categoriesGoods = categoriesGoods;
    }

    public List<GoodsEntity> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsEntity> goods) {
        this.goods = goods;
    }

    public List<CategoriesPropertiesEntity> getCategoriesPropertiesEntities() {
        return categoriesProperties;
    }

    public void setCategoriesPropertiesEntities(List<CategoriesPropertiesEntity> categoriesProperties) {
        this.categoriesProperties = categoriesProperties;
    }

    public List<PropertiesNameGoodsEntity> getPropertiesNameGoodsEntities() {
        return propertiesNameGoods;
    }

    public void setPropertiesNameGoodsEntities(List<PropertiesNameGoodsEntity> propertiesNameGoods) {
        this.propertiesNameGoods = propertiesNameGoods;
    }
}
