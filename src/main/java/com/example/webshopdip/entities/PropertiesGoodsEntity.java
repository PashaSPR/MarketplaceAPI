package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

/**
 * Клас, що представляє сутність "Властивості, Атрибути Товарів".
 * Містить дані та Властивості та Атрибути товару, які описують характеристику товару.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "propertiesgoods")
public class PropertiesGoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Властивостей, Атрибутів
    private String value; // Значення Властивості, Атрибуту
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Властивостей, Атрибутів може стосуватися одного Товару
    private GoodsEntity goods;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "propertiesNameGoods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Властивостей, Атрибутів може стосуватися одного Імені Властивостей
    private PropertiesNameGoodsEntity propertiesNameGoods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }

    public PropertiesNameGoodsEntity getPropertiesNameGoods() {
        return propertiesNameGoods;
    }

    public void setPropertiesNameGoods(PropertiesNameGoodsEntity propertiesNameGoods) {
        this.propertiesNameGoods = propertiesNameGoods;
    }
}
