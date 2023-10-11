package com.example.webshopdip.services;

import com.example.webshopdip.dtos.CategoriesGoodsDTO;
import com.example.webshopdip.dtos.GoodsGetAllDTO;
import com.example.webshopdip.entities.CategoriesGoodsEntity;
import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.exceptions.CategoriesGoodsAlreadyExistException;
import com.example.webshopdip.exceptions.CategoriesGoodsNotFoundException;
import com.example.webshopdip.repositories.CategoriesGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesGoodsService {
    @Autowired
    private CategoriesGoodsRepository categoriesGoodsRepository;
    @Autowired
    private GoodsService goodsService;


//    public CategoriesGoodsEntity categoriesGoodsRegistration (CategoriesGoodsEntity categoriesGoodsEntity) throws CategoriesGoodsAlreadyExistException {
//        if (categoriesGoodsRepository.findByName(categoriesGoodsEntity.getName()) != null){
//            throw new CategoriesGoodsAlreadyExistException("Така категорія товару вже існує");
//        }
//        return categoriesGoodsRepository.save(categoriesGoodsEntity);
//    }

    public CategoriesGoodsDTO createCategoriesGoods(CategoriesGoodsDTO dto) throws CategoriesGoodsAlreadyExistException {
        if (categoriesGoodsRepository.findByName(dto.getName()) != null) {
            throw new CategoriesGoodsAlreadyExistException("Така категорія вже існує");
        }

        CategoriesGoodsEntity entity = new CategoriesGoodsEntity();
        entity.setName(dto.getName());

        CategoriesGoodsEntity savedEntity = categoriesGoodsRepository.save(entity);

        return entityToDTO(savedEntity);
    }
    public CategoriesGoodsDTO entityToDTO(CategoriesGoodsEntity entity) {
        CategoriesGoodsDTO dto = new CategoriesGoodsDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }

//    public CategoriesGoodsEntity getOne (Long id) throws CategoriesGoodsNotFoundException {
//        CategoriesGoodsEntity categoriesGoodsEntity = categoriesGoodsRepository.findById(id).get();
//        if (categoriesGoodsEntity == null){
//            throw new CategoriesGoodsNotFoundException("Категорію товару не знайдено");
//        }
//        return categoriesGoodsEntity;
//    }
    public CategoriesGoodsEntity getOne(Long id) throws CategoriesGoodsNotFoundException {
        Optional<CategoriesGoodsEntity> optional = categoriesGoodsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CategoriesGoodsNotFoundException("Категорію товару не знайдено");
        }
        return optional.get();
    }

    public CategoriesGoodsEntity getGoodsByCategoriesId(Long id) throws CategoriesGoodsNotFoundException {
        Optional<CategoriesGoodsEntity> optional = categoriesGoodsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CategoriesGoodsNotFoundException("Категорію товару не знайдено");
        }
        return optional.get();
    }
}
