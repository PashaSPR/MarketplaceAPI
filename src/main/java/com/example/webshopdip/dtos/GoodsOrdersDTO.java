package com.example.webshopdip.dtos;

import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.entities.GoodsInvoicesEntity;
import com.example.webshopdip.entities.OrdersListsEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class GoodsOrdersDTO {
    private Long id; // Унікальний ідентифікатор Переліку товару в замовлені
    // Зв'язок Many-to-One: Багато Переліків товару може стосуватися одного Замовлення покупця
    private OrdersListsDTO ordersListsDTO;
    // Зв'язок Many-to-One: Багато Переліків товару може належати одному Товар з Магазину
    private GoodsInvoicesDTO goodsInvoicesDTO;

    private Float price;
    private Integer quantity;

    public GoodsOrdersDTO() {
    }

    public GoodsOrdersDTO(Long id, OrdersListsDTO ordersListsDTO, GoodsInvoicesDTO goodsInvoicesDTO, Float price, Integer quantity) {
        this.id = id;
        this.ordersListsDTO = ordersListsDTO;
        this.goodsInvoicesDTO = goodsInvoicesDTO;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdersListsDTO getOrdersListsDTO() {
        return ordersListsDTO;
    }

    public void setOrdersListsDTO(OrdersListsDTO ordersListsDTO) {
        this.ordersListsDTO = ordersListsDTO;
    }

    public GoodsInvoicesDTO getGoodsInvoicesDTO() {
        return goodsInvoicesDTO;
    }

    public void setGoodsInvoicesDTO(GoodsInvoicesDTO goodsInvoicesDTO) {
        this.goodsInvoicesDTO = goodsInvoicesDTO;
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
