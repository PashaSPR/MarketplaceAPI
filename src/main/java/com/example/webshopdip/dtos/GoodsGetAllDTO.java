package com.example.webshopdip.dtos;

import java.util.List;

public class GoodsGetAllDTO {
    private Long id; // Унікальний ідентифікатор Товару
    private String name; // Назва Товару
    private String short_discription; // Короткий опис Товару
    //    private Long descriptionsGoodsId;
//    private SubcategoriesGoodsDTO subcategoriesGoodsDTO;
    private Long subcategoryId;
    private String subcategoriesGoodsName; // Назва Підкатегорії Товару
    private List<PhotosGoodsDTO> photosGoodsDTOS;
    private List<PropertiesGoodsDTO> propertiesGoodsDTOS;
//    private List<ValueTypePropertiesEnum.ValueType> valueTypeList;

    public GoodsGetAllDTO() {
    }

//    public GoodsGetAllDTO(Long id, String name, String short_discription, SubcategoriesGoodsDTO subcategoriesGoodsDTO, List<PhotosGoodsDTO> photosGoodsDTOS, List<PropertiesGoodsDTO> propertiesGoodsDTOS) {
//        this.id = id;
//        this.name = name;
//        this.short_discription = short_discription;
//        this.subcategoriesGoodsDTO = subcategoriesGoodsDTO;
//        this.photosGoodsDTOS = photosGoodsDTOS;
//        this.propertiesGoodsDTOS = propertiesGoodsDTOS;
//    }

        public GoodsGetAllDTO(
            Long id,
            String name,
            String short_discription,
            Long subcategoryId,
            String subcategoriesGoodsName,
            List<PhotosGoodsDTO> photosGoodsDTOS,
            List<PropertiesGoodsDTO> propertiesGoodsDTOS) {
        this.id = id;
        this.name = name;
        this.short_discription = short_discription;
        this.subcategoryId = subcategoryId;
        this.subcategoriesGoodsName = subcategoriesGoodsName;
        this.photosGoodsDTOS = photosGoodsDTOS;
        this.propertiesGoodsDTOS = propertiesGoodsDTOS;
    }



//    public GoodsGetAllDTO(
//            Long id,
//            String name,
//            String short_discription,
//            String subcategoriesGoodsName,
//            List<PhotosGoodsDTO> photosGoodsDTOS,
//            List<PropertiesGoodsDTO> propertiesGoodsDTOS,
//            List<ValueTypePropertiesEnum.ValueType> valueTypeList) {
//        this.id = id;
//        this.name = name;
//        this.short_discription = short_discription;
//        this.subcategoriesGoodsName = subcategoriesGoodsName;
//        this.photosGoodsDTOS = photosGoodsDTOS;
//        this.propertiesGoodsDTOS = propertiesGoodsDTOS;
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

    public String getShort_discription() {
        return short_discription;
    }

    public void setShort_discription(String short_discription) {
        this.short_discription = short_discription;
    }

//    public SubcategoriesGoodsDTO getSubcategoriesGoodsDTO() {
//        return subcategoriesGoodsDTO;
//    }
//
//    public void setSubcategoriesGoodsDTO(SubcategoriesGoodsDTO subcategoriesGoodsDTO) {
//        this.subcategoriesGoodsDTO = subcategoriesGoodsDTO;
//    }


    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getSubcategoriesGoodsName() {
        return subcategoriesGoodsName;
    }

    public void setSubcategoriesGoodsName(String subcategoriesGoodsName) {
        this.subcategoriesGoodsName = subcategoriesGoodsName;
    }

    public List<PhotosGoodsDTO> getPhotosGoodsDTOS() {
        return photosGoodsDTOS;
    }

    public void setPhotosGoodsDTOS(List<PhotosGoodsDTO> photosGoodsDTOS) {
        this.photosGoodsDTOS = photosGoodsDTOS;
    }

    public List<PropertiesGoodsDTO> getPropertiesGoodsDTOS() {
        return propertiesGoodsDTOS;
    }

    public void setPropertiesGoodsDTOS(List<PropertiesGoodsDTO> propertiesGoodsDTOS) {
        this.propertiesGoodsDTOS = propertiesGoodsDTOS;
    }

//    public List<ValueTypePropertiesEnum.ValueType> getValueTypeList() {
//        return valueTypeList;
//    }
//
//    public void setValueTypeList(List<ValueTypePropertiesEnum.ValueType> valueTypeList) {
//        this.valueTypeList = valueTypeList;
//    }
}
