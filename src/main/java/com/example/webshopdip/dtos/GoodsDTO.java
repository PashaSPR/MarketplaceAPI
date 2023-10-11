package com.example.webshopdip.dtos;

import java.util.List;

public class GoodsDTO {
    private Long id; // Унікальний ідентифікатор Товару
    private String name; // Назва Товару
    private String short_discription; // Короткий опис Товару
//    private Long descriptionsGoodsId;
    private Long subcategoryId;
    private List<PhotosGoodsDTO> photosGoods;
    private List<PropertiesGoodsDTO> propertiesGoods;

    public GoodsDTO() {
    }

    public GoodsDTO(
            Long id,
            String name,
            String short_discription,
            Long subcategoryId,
            List<PhotosGoodsDTO> photosGoods,
            List<PropertiesGoodsDTO> propertiesGoods) {
        this.id = id;
        this.name = name;
        this.short_discription = short_discription;
        this.subcategoryId = subcategoryId;
        this.photosGoods = photosGoods;
        this.propertiesGoods = propertiesGoods;
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

    public String getShort_discription() {
        return short_discription;
    }

    public void setShort_discription(String short_discription) {
        this.short_discription = short_discription;
    }

    public Long getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Long subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public List<PhotosGoodsDTO> getPhotosGoods() {
        return photosGoods;
    }

    public void setPhotosGoods(List<PhotosGoodsDTO> photosGoods) {
        this.photosGoods = photosGoods;
    }

    public List<PropertiesGoodsDTO> getPropertiesGoods() {
        return propertiesGoods;
    }

    public void setPropertiesGoods(List<PropertiesGoodsDTO> propertiesGoods) {
        this.propertiesGoods = propertiesGoods;
    }
}
