package com.example.webshopdip.dtos;

public class GoodsInvoicesDTO {
    private Long id; // Унікальний ідентифікатор Переліку товару
    private GoodsGetAllDTO goods; // Товар
    private Float price; // Ціна товару
    private Integer quantity; // Кількість товару

    public GoodsInvoicesDTO() {
    }

    public GoodsInvoicesDTO(Long id, GoodsGetAllDTO goods, Float price, Integer quantity) {
        this.id = id;
        this.goods = goods;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoodsGetAllDTO getGoods() {
        return goods;
    }

    public void setGoods(GoodsGetAllDTO goods) {
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
