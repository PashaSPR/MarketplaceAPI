package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;

/**
 * Клас, що представляє сутність "Перелік Товарів в замовленні Покупця".
 * Містить дані про Товари які замовляє Покупець.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "goodsorders")
public class GoodsOrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Переліку товару в замовлені
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ordersLists_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Переліків товару може стосуватися одного Замовлення покупця
    private OrdersListsEntity ordersLists;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "goodsInvoices_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Переліків товару може належати одному Товар з Магазину
    private GoodsInvoicesEntity goodsInvoices;

    private Float price;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdersListsEntity getOrdersLists() {
        return ordersLists;
    }

    public void setOrdersLists(OrdersListsEntity ordersLists) {
        this.ordersLists = ordersLists;
    }

    public GoodsInvoicesEntity getGoodsInvoices() {
        return goodsInvoices;
    }

    public void setGoodsInvoices(GoodsInvoicesEntity goodsInvoices) {
        this.goodsInvoices = goodsInvoices;
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
