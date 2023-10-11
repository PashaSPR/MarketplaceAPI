package com.example.webshopdip.dtos;

public class PropertiesNameGoodsGetAllDTO {
    private Long id; // Унікальний ідентифікатор Назви властивості Товару
    private String name; // Назва властивості Товару
    private String valueType; // Тип значення Властивості, Атрибуту

    public PropertiesNameGoodsGetAllDTO() {
    }

    public PropertiesNameGoodsGetAllDTO(Long id, String name, String valueType) {
        this.id = id;
        this.name = name;
        this.valueType = valueType;
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

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
