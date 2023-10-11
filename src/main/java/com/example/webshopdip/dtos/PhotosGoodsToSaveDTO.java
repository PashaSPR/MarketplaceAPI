package com.example.webshopdip.dtos;

public class PhotosGoodsToSaveDTO {
    private Long id; // Унікальний ідентифікатор Фотографії товару
    private String description; // Опис Фотографії товару
    private String path; // Шлях до файлу Фотографії товару
    private Long goodsId; // Унікальний ідентифікатор товару

    public PhotosGoodsToSaveDTO() {
    }


    public PhotosGoodsToSaveDTO(Long id, String description, String path, Long goodsId) {
        this.id = id;
        this.description = description;
        this.path = path;
        this.goodsId = goodsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long doodsId) {
        this.goodsId = doodsId;
    }
}
