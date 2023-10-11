package com.example.webshopdip.dtos;

import com.example.webshopdip.enums.ValueTypePropertiesEnum;
import javax.persistence.*;

import java.util.List;

public class PropertiesNameGoodsDTO {
    private Long id; // Унікальний ідентифікатор Назви властивості Товару
    private String name; // Назва властивості Товару
    private String valueType; // Тип значення Властивості, Атрибуту


    // Зв'язок Many-to-Many: Багато імен Властивостей Товарів може стосуватися одній Підкатегорії
//    private List<SubcategoriesGoodsDTO> subcategoriesGoodsDTOS;
    private List<ValueTypePropertiesEnum.ValueType> valueTypeList;

//    ///////сутності що мають відношення One-to-Many з сутністю PropertiesNameGoods
//
//    // Зв'язок One-to-Many: Одне ім'я Властивості товару може відноситися до багатьох Властивостей Товарів
//    private List<PropertiesGoodsEntity> propertiesGoodsEntities = new ArrayList<>();
//
//
//    // Зв'язок One-to-Many: Одна ім'я Властивості товару  може відноситися до багатьох Зв'язків
//    // між категоріями та типами властивостей
//    private List<CategoriesPropertiesEntity> categoriesPropertiesEntities = new ArrayList<>();


    public PropertiesNameGoodsDTO() {
    }

//    public PropertiesNameGoodsDTO(Long id, String name, List<SubcategoriesGoodsDTO> subcategoriesGoodsDTOS) {
//        this.id = id;
//        this.name = name;
//        this.subcategoriesGoodsDTOS = subcategoriesGoodsDTOS;
//    }

    public PropertiesNameGoodsDTO(Long id, String name, String valueType, List<ValueTypePropertiesEnum.ValueType> valueTypeList) {
        this.id = id;
        this.name = name;
        this.valueType = valueType;
        this.valueTypeList = valueTypeList;
    }


//    public PropertiesNameGoodsDTO(Long id, String name, String valueType, List<SubcategoriesGoodsDTO> subcategoriesGoodsDTOS, List<ValueTypePropertiesEnum.ValueType> valueTypeList) {
//        this.id = id;
//        this.name = name;
//        this.valueType = valueType;
//        this.subcategoriesGoodsDTOS = subcategoriesGoodsDTOS;
//        this.valueTypeList = valueTypeList;
//    }

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

//    public List<SubcategoriesGoodsDTO> getSubcategoriesGoodsDTOS() {
//        return subcategoriesGoodsDTOS;
//    }
//
//    public void setSubcategoriesGoodsDTOS(List<SubcategoriesGoodsDTO> subcategoriesGoodsDTOS) {
//        this.subcategoriesGoodsDTOS = subcategoriesGoodsDTOS;
//    }

    public List<ValueTypePropertiesEnum.ValueType> getValueTypeList() {
        return valueTypeList;
    }

    public void setValueTypeList(List<ValueTypePropertiesEnum.ValueType> valueTypeList) {
        this.valueTypeList = valueTypeList;
    }
}
