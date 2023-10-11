package com.example.webshopdip.dtos;

public class GoodsForCatalogDTO {
    private Long id; // Унікальний ідентифікатор Товару
    private String name; // Назва Товару

    public GoodsForCatalogDTO() {
    }

    public GoodsForCatalogDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
}
