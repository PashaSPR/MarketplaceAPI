package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

/**
 * Клас, що представляє сутність "Фотографії Товарів".
 * Містить дані та поведінку, пов'язані з товаром.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "photosgoods")
public class PhotosGoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Фотографії товару
    private String description; // Опис Фотографії товару
    private String path; // Шлях до файлу Фотографії товару
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Фотографій може стосуватися одного Товару
    private GoodsEntity goods;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }
}
