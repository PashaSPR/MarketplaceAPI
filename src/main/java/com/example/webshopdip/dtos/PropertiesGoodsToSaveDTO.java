package com.example.webshopdip.dtos;

public class PropertiesGoodsToSaveDTO {
    private Long id; // Унікальний ідентифікатор Властивостей, Атрибутів
    private String propertiesName; // Тип значення Властивості, Атрибуту
    private String type; // Тип значення Властивості, Атрибуту
    private String value; // Значення Властивості, Атрибуту

    // Зв'язок Many-to-One: Багато Властивостей, Атрибутів може стосуватися одного Товару
    private Long goodsId;

    // Зв'язок Many-to-One: Багато Властивостей, Атрибутів може стосуватися одного Імені Властивостей
    private Long propertiesNameGoodsId;

    public PropertiesGoodsToSaveDTO() {
    }

    public PropertiesGoodsToSaveDTO(Long id, String type, String value, Long goodsId, Long propertiesNameGoodsId) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.goodsId = goodsId;
        this.propertiesNameGoodsId = propertiesNameGoodsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getPropertiesNameGoodsId() {
        return propertiesNameGoodsId;
    }

    public void setPropertiesNameGoodsId(Long propertiesNameGoodsId) {
        this.propertiesNameGoodsId = propertiesNameGoodsId;
    }
}
