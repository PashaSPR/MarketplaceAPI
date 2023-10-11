package com.example.webshopdip.dtos;

public class PropertiesGoodsDTO {

    private Long id; // Унікальний ідентифікатор Властивостей, Атрибутів
    private String propertiesName; // Тип значення Властивості, Атрибуту
    private String type; // Тип значення Властивості, Атрибуту
    private String value; // Значення Властивості, Атрибуту

    public PropertiesGoodsDTO() {
    }

    public PropertiesGoodsDTO(Long id, String propertiesName, String type, String value) {
        this.id = id;
        this.propertiesName = propertiesName;
        this.type = type;
        this.value = value;
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
}
