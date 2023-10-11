package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Переліки Товарів що надходять до магазину".
 * Містить дані про Товари що надходять до магазину.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "goodsinvoices")
public class GoodsInvoicesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Переліку товару
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Накладних на товар може належати одному Товару
    private GoodsEntity goods;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "sellers_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Накладних на товар може належати одному Продавцю
    private SellersEntity sellers;


    /////////сутності що мають відношення One-to-Many з сутністю Goods
    @OneToMany(mappedBy = "goodsInvoices")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один товар може належати до багатьох Переліків Товарів в замовленні Покупця
    private List<GoodsOrdersEntity> goodsOrders = new ArrayList<>();




    private Float price; // Ціна товару
    private Integer quantity; // Кількість товару

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
