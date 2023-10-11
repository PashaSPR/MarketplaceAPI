package com.example.webshopdip.services;

import com.example.webshopdip.dtos.PropertiesGoodsToSaveDTO;
import com.example.webshopdip.entities.*;
import com.example.webshopdip.exceptions.PropertiesGoodsNotFoundException;
import com.example.webshopdip.repositories.PropertiesGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertiesGoodsService {
    @Autowired
    private PropertiesGoodsRepository propertiesGoodsRepository;

    public PropertiesGoodsEntity createPropertiesGoods(PropertiesGoodsToSaveDTO propertiesGoodsToSaveDTO) {
        PropertiesGoodsEntity propertiesGoodsEntity = new PropertiesGoodsEntity();
//        propertiesGoodsEntity.setType(propertiesGoodsDTO.getType());
//        propertiesGoodsEntity.setValue(propertiesGoodsToSaveDTO.getValue());

        GoodsEntity goodsEntity = new GoodsEntity();
//        goodsEntity.setId(propertiesGoodsDTO.getGoodsId());
        propertiesGoodsEntity.setGoods(goodsEntity);

        PropertiesNameGoodsEntity propertiesNameGoodsEntity = new PropertiesNameGoodsEntity();
        propertiesNameGoodsEntity.setId(propertiesGoodsToSaveDTO.getPropertiesNameGoodsId());
        propertiesGoodsEntity.setPropertiesNameGoods(propertiesNameGoodsEntity);

        return propertiesGoodsRepository.save(propertiesGoodsEntity);
    }

    public PropertiesGoodsEntity getOne(Long id) throws PropertiesGoodsNotFoundException {
        return propertiesGoodsRepository.findById(id)
                .orElseThrow(() -> new PropertiesGoodsNotFoundException("Властивості товару не знайдено"));
    }

    public void deletePropertiesGoods(Long id) {
        propertiesGoodsRepository.deleteById(id);
    }

    public List<PropertiesGoodsEntity> getPropertiesByGoodsId(Long goodsId){
        return propertiesGoodsRepository.findByGoodsId(goodsId);
    }

//    public CategoriesPropertiesDTO getOne(Long id) throws CategoriesPropertiesNotFoundException {
//        Optional<CategoriesPropertiesEntity> optional = categoriesPropertiesRepository.findById(id);
//
//        if (optional.isEmpty()) {
//            throw new CategoriesPropertiesNotFoundException("No record found");
//        }
//
//        CategoriesPropertiesEntity entity = optional.get();
//        return entityToDTO(entity);
//    }


}