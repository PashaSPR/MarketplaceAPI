package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Назва Властивостей, Атрибутів Товарів".
 * Містить Назви Властивостей, Атрибутів Товарів, які описують характеристику товару.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "propertiesnamegoods")
public class PropertiesNameGoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Назви властивості Товару
    private String name; // Назва властивості Товару
    @Column(name = "valueType")
    private String valueType; // Тип значення Властивості, Атрибуту

    @ManyToMany(mappedBy = "propertiesNameGoods")
    @JsonIgnore
//    @JsonBackReference
    // Зв'язок Many-to-Many: Багато Властивостей товару можуть відноситися до багатьох Категорій
    private List<SubcategoriesGoodsEntity> subcategoriesGoods = new ArrayList<>();

    ///////сутності що мають відношення One-to-Many з сутністю PropertiesNameGoods
    @OneToMany(mappedBy = "propertiesNameGoods", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    // Зв'язок One-to-Many: Одне ім'я Властивості товару може відноситися до багатьох Властивостей Товарів
    private List<PropertiesGoodsEntity> propertiesGoods = new ArrayList<>();
    @OneToMany(mappedBy = "propertiesNameGoods", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    // Зв'язок One-to-Many: Одна ім'я Властивості товару  може відноситися до багатьох Зв'язків
    // між категоріями та типами властивостей
    private List<CategoriesPropertiesEntity> categoriesProperties = new ArrayList<>();

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

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public List<SubcategoriesGoodsEntity> getSubcategoriesGoods() {
        return subcategoriesGoods;
    }

    public void setSubcategoriesGoods(List<SubcategoriesGoodsEntity> subcategoriesGoods) {
        this.subcategoriesGoods = subcategoriesGoods;
    }

    public List<PropertiesGoodsEntity> getPropertiesGoods() {
        return propertiesGoods;
    }

    public List<CategoriesPropertiesEntity> getCategoriesProperties() {
        return categoriesProperties;
    }

    public void setPropertiesGoods(List<PropertiesGoodsEntity> propertiesGoods) {
        this.propertiesGoods = propertiesGoods;
    }

    public void setCategoriesProperties(List<CategoriesPropertiesEntity> categoriesProperties) {
        this.categoriesProperties = categoriesProperties;
    }
}
