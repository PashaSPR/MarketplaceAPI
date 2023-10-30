package com.example.webshopdip.dtos;

public class GoodsOrdersToSaveDTO {
    private Long id; // Унікальний ідентифікатор Переліку товару в замовлені
    // Зв'язок Many-to-One: Багато Переліків товару може стосуватися одного Замовлення покупця
//    private OrdersListsDTO ordersListsDTO;
//    // Зв'язок Many-to-One: Багато Переліків товару може належати одному Товар з Магазину
//    private GoodsInvoicesDTO goodsInvoicesDTO;

    private Long goodsInvoicesId;
    private Long ordersListsId;
    private Float price;
    private Integer quantity;

    public GoodsOrdersToSaveDTO() {
    }

    public GoodsOrdersToSaveDTO(Long id, Long goodsInvoicesId, Long ordersListsId, Float price, Integer quantity) {
        this.id = id;
        this.goodsInvoicesId = goodsInvoicesId;
        this.ordersListsId = ordersListsId;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsInvoicesId() {
        return goodsInvoicesId;
    }

    public void setGoodsInvoicesId(Long goodsInvoicesId) {
        this.goodsInvoicesId = goodsInvoicesId;
    }

    public Long getOrdersListsId() {
        return ordersListsId;
    }

    public void setOrdersListsId(Long ordersListsId) {
        this.ordersListsId = ordersListsId;
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
