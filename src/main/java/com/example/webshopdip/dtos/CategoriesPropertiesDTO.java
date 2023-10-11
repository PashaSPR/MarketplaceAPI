package com.example.webshopdip.dtos;

public class CategoriesPropertiesDTO {

    private Long id; // Унікальний ідентифікатор категорії товару

    // Зв'язок Many-to-One: Багато зв'язків може стосуватися одній Субкатегорії
    private Long subcategoriesGoodsId;

    // Зв'язок Many-to-One: Багато зв'язків може стосуватися одній Субкатегорії
    private Long propertiesNameGoodsId;

    public CategoriesPropertiesDTO() {
    }

    public CategoriesPropertiesDTO(Long id, Long subcategoriesGoodsId, Long propertiesNameGoodsId) {
        this.id = id;
        this.subcategoriesGoodsId = subcategoriesGoodsId;
        this.propertiesNameGoodsId = propertiesNameGoodsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubcategoriesGoodsId() {
        return subcategoriesGoodsId;
    }

    public void setSubcategoriesGoodsId(Long subcategoriesGoodsId) {
        this.subcategoriesGoodsId = subcategoriesGoodsId;
    }

    public Long getPropertiesNameGoodsId() {
        return propertiesNameGoodsId;
    }

    public void setPropertiesNameGoodsId(Long propertiesNameGoodsId) {
        this.propertiesNameGoodsId = propertiesNameGoodsId;
    }
}
