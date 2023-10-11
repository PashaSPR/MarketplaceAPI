//package com.example.mayprojegtdip.services;
//
//import com.example.mayprojegtdip.dto.SubcategoriesGoodsDTO;
//import com.example.mayprojegtdip.entities.SubcategoriesGoodsEntity;
//import com.example.mayprojegtdip.exceptions.SubcategoriesGoodsAlreadyExistException;
//import com.example.mayprojegtdip.exceptions.SubcategoriesGoodsNotFoundException;
//import com.example.mayprojegtdip.repo.CategoriesGoodsRepository;
//import com.example.mayprojegtdip.repo.SubcategoriesGoodsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class SubcategoriesGoodsService {
//    @Autowired
//    private SubcategoriesGoodsRepository subcategoriesGoodsRepository;
//    @Autowired
//    private CategoriesGoodsRepository categoriesGoodsRepository;
//
//    public SubcategoriesGoodsEntity subcategoriesGoodsRegistration(SubcategoriesGoodsEntity subcategoriesGoodsEntity) throws SubcategoriesGoodsAlreadyExistException {
//        if (subcategoriesGoodsRepository.findByName(subcategoriesGoodsEntity.getName()) != null) {
//            throw new SubcategoriesGoodsAlreadyExistException("Така підкатегорія вже існує");
//        }
//        return subcategoriesGoodsRepository.save(subcategoriesGoodsEntity);
//    }
//
////    public SubcategoriesGoodsEntity createSubcategoriesGoods(SubcategoriesGoodsEntity subcategoriesGoodsEntity,
////                                                             Long categoriesGoodsId) {
////        CategoriesGoodsEntity categoriesGoodsEntity = categoriesGoodsRepository.findById(categoriesGoodsId).get();
////        subcategoriesGoodsEntity.setCategoriesGoods(categoriesGoodsEntity);
////        return subcategoriesGoodsRepository.save(subcategoriesGoodsEntity);
////    }
//
//
//
//    public SubcategoriesGoodsEntity getOne(Long id) throws SubcategoriesGoodsNotFoundException {
//        Optional<SubcategoriesGoodsEntity> optional = subcategoriesGoodsRepository.findById(id);
//        if (optional.isEmpty()) {
//            throw new SubcategoriesGoodsNotFoundException("Підкатегорію не знайдено");
//        }
//        return optional.get();
//    }
//
//    public SubcategoriesGoodsDTO entityToDTO(SubcategoriesGoodsEntity entity) {
//        SubcategoriesGoodsDTO dto = new SubcategoriesGoodsDTO();
//        dto.setId(entity.getId());
//        dto.setName(entity.getName());
//
//        // Тут також можна встановити propertiesNameGoodsDTO, якщо ви використовуєте їх DTO
//        return dto;
//    }
//
////    public SubcategoriesGoodsEntity createSubcategoriesGoods(SubcategoriesGoodsEntity subcategoriesGoodsEntity,
////                                                             Long categoriesGoodsId) throws CategoriesGoodsNotFoundException {
////        CategoriesGoodsEntity categoriesGoodsEntity = categoriesGoodsRepository.findById(categoriesGoodsId)
////                .orElseThrow(() -> new CategoriesGoodsNotFoundException("Категорію товару не знайдено"));
////
////        subcategoriesGoodsEntity.setCategoriesGoods(categoriesGoodsEntity);
////        return subcategoriesGoodsRepository.save(subcategoriesGoodsEntity);
////    }
//
//
//    public SubcategoriesGoodsEntity getOneWithProperties(Long id) throws SubcategoriesGoodsNotFoundException {
//        Optional<SubcategoriesGoodsEntity> optional = subcategoriesGoodsRepository.findById(id);
//
//        if (optional.isEmpty()) {
//            throw new SubcategoriesGoodsNotFoundException("Підкатегорію не знайдено");
//        }
//
//        SubcategoriesGoodsEntity subcategory = optional.get();
//        subcategory.getPropertiesNameGoodsEntities().size(); // Завантажуємо зв'язані дані
//
//        return subcategory;
//    }
//}
//
//
//
//
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////


package com.example.webshopdip.services;

import com.example.webshopdip.dtos.GoodsForCatalogDTO;
import com.example.webshopdip.dtos.PropertiesNameGoodsDTO;
import com.example.webshopdip.dtos.SubcategoriesForCatalogDTO;
import com.example.webshopdip.dtos.SubcategoriesGoodsDTO;
import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.entities.PropertiesNameGoodsEntity;
import com.example.webshopdip.entities.SubcategoriesGoodsEntity;
import com.example.webshopdip.exceptions.*;
import com.example.webshopdip.repositories.CategoriesGoodsRepository;
import com.example.webshopdip.repositories.PropertiesNameGoodsRepository;
import com.example.webshopdip.repositories.SubcategoriesGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubcategoriesGoodsService {
    @Autowired
    private SubcategoriesGoodsRepository subcategoriesGoodsRepository;
    @Autowired
    private PropertiesNameGoodsRepository propertiesNameGoodsRepository;
    @Autowired
    private CategoriesGoodsRepository categoriesGoodsRepository;
    @Autowired
    private PropertiesNameGoodsService propertiesNameGoodsService;

    public SubcategoriesGoodsDTO createSubcategoriesGoods(SubcategoriesGoodsDTO dto) throws SubcategoriesGoodsAlreadyExistException {
        if (subcategoriesGoodsRepository.findByName(dto.getName()) != null) {
            throw new SubcategoriesGoodsAlreadyExistException("Така підкатегорія вже існує");
        }

        SubcategoriesGoodsEntity entity = new SubcategoriesGoodsEntity();
        entity.setName(dto.getName());
        entity.setCategoriesGoods(categoriesGoodsRepository.findById(dto.getCategoriesGoodsId()).orElse(null));

        SubcategoriesGoodsEntity savedEntity = subcategoriesGoodsRepository.save(entity);

        return entityToDTO(savedEntity);
    }

//    public SubcategoriesGoodsDTO getOne(Long id) throws SubcategoriesGoodsNotFoundException {
//        SubcategoriesGoodsEntity entity = subcategoriesGoodsRepository.findById(id)
//                .orElseThrow(() -> new SubcategoriesGoodsNotFoundException("Підкатегорію не знайдено"));
//
//        return entityToDTO(entity);
//    }


    //    public SubcategoriesGoodsDTO getOneWithProperties(Long id) throws SubcategoriesGoodsNotFoundException {
//        SubcategoriesGoodsEntity entity = subcategoriesGoodsRepository.findById(id)
//                .orElseThrow(() -> new SubcategoriesGoodsNotFoundException("Підкатегорію не знайдено"));
//
//        entity.getPropertiesNameGoodsEntities().size(); // Завантажуємо зв'язані дані
//
//        return entityToDTO(entity);
//    }
    public SubcategoriesGoodsDTO getOne(Long id) throws SubcategoriesGoodsNotFoundException {
        SubcategoriesGoodsEntity entity = subcategoriesGoodsRepository.findById(id)
                .orElseThrow(() -> new SubcategoriesGoodsNotFoundException("Підкатегорію не знайдено"));

        entity.getPropertiesNameGoodsEntities().size(); // Завантажуємо зв'язані дані

        SubcategoriesGoodsDTO dto = entityToDTO(entity);
        dto.setPropertiesNameGoodsDTOS(propertiesNameGoodsToDTOList(entity.getPropertiesNameGoodsEntities()));

        return dto;
    }
    public List<SubcategoriesGoodsDTO> getByCategoryId(Long id) {
        List<SubcategoriesGoodsEntity> entities = subcategoriesGoodsRepository.findByCategoriesGoods_Id(id);
        List<SubcategoriesGoodsDTO> dtos = new ArrayList<>();
        for (SubcategoriesGoodsEntity entity: entities){
            entity.getPropertiesNameGoodsEntities().size();// Завантажуємо зв'язані дані
            SubcategoriesGoodsDTO dto = entityToDTO(entity);
            dto.setPropertiesNameGoodsDTOS(propertiesNameGoodsToDTOList(entity.getPropertiesNameGoodsEntities()));
            dtos.add(dto);
        }
        return dtos;
    }

    public List<SubcategoriesForCatalogDTO> getGoodsByCategoriesId(Long id) {
        List<SubcategoriesGoodsEntity> entities = subcategoriesGoodsRepository.findByCategoriesGoods_Id(id);
        List<SubcategoriesForCatalogDTO> dtos = new ArrayList<>();

        for (SubcategoriesGoodsEntity entity: entities){
            entity.getPropertiesNameGoodsEntities().size();// Завантажуємо зв'язані дані

            SubcategoriesForCatalogDTO dto = new SubcategoriesForCatalogDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());

            List<GoodsForCatalogDTO> goods = new ArrayList<>();
            for (GoodsEntity goodsEntity: entity.getGoods()){
                GoodsForCatalogDTO good = new GoodsForCatalogDTO(goodsEntity.getId(), goodsEntity.getName());
                goods.add(good);
            }
            dto.setGoods(goods);
            dtos.add(dto);
        }
        return dtos;
    }



    // Цей метод конвертує список сутностей в список DTO
    private List<PropertiesNameGoodsDTO> propertiesNameGoodsToDTOList(List<PropertiesNameGoodsEntity> entities) {
        List<PropertiesNameGoodsDTO> dtos = new ArrayList<>();
        for (PropertiesNameGoodsEntity entity : entities) {
            PropertiesNameGoodsDTO dto = new PropertiesNameGoodsDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            // інші поля DTO
            dtos.add(dto);
        }
        return dtos;
    }


    public SubcategoriesGoodsDTO entityToDTO(SubcategoriesGoodsEntity subcategoriesGoodsEntity) {
        SubcategoriesGoodsDTO subcategoriesGoodsDTO = new SubcategoriesGoodsDTO();
        subcategoriesGoodsDTO.setId(subcategoriesGoodsEntity.getId());
        subcategoriesGoodsDTO.setName(subcategoriesGoodsEntity.getName());
        subcategoriesGoodsDTO.setCategoriesGoodsId(subcategoriesGoodsEntity.getCategoriesGoods().getId());

        List<PropertiesNameGoodsEntity> propertiesNameGoodsEntities = subcategoriesGoodsEntity.getPropertiesNameGoodsEntities();
        List<PropertiesNameGoodsDTO> propertiesNameGoodsDTOS = new ArrayList<>();

        for (PropertiesNameGoodsEntity propertiesNameGoods : propertiesNameGoodsEntities) {
            PropertiesNameGoodsDTO propertiesNameGoodsDTO = new PropertiesNameGoodsDTO();
            propertiesNameGoodsDTO.setId(propertiesNameGoods.getId());
            propertiesNameGoodsDTO.setName(propertiesNameGoods.getName());
            propertiesNameGoodsDTOS.add(propertiesNameGoodsDTO);
        }
        subcategoriesGoodsDTO.setPropertiesNameGoodsDTOS(propertiesNameGoodsDTOS);


//        dto.setPropertiesNameGoodsDTOS(subcategoriesGoodsEntity.getPropertiesNameGoodsEntities());
        return subcategoriesGoodsDTO;
    }
//    public SubcategoriesGoodsDTO addPropertiesToSubcategory(Long id, List<Long> propertiesIds) throws SubcategoriesGoodsNotFoundException, PropertiesNameGoodsNotFoundException {
//        SubcategoriesGoodsEntity entity = subcategoriesGoodsRepository.findById(id)
//                .orElseThrow(() -> new SubcategoriesGoodsNotFoundException("Підкатегорію не знайдено"));
//
//        List<PropertiesNameGoodsEntity> propertiesEntities = new ArrayList<>();
//        for (Long propertyId : propertiesIds) {
//            PropertiesNameGoodsEntity propertyEntity = propertiesNameGoodsRepository.findById(propertyId)
//                    .orElseThrow(() -> new PropertiesNameGoodsNotFoundException("Властивість товару не знайдено"));
//            propertiesEntities.add(propertyEntity);
//        }
//
////        entity.getPropertiesNameGoodsEntities().addAll(propertiesEntities);
//        subcategoriesGoodsRepository.save(entity);
//
//        return entityToDTO(entity);
//    }

    //    public SubcategoriesGoodsDTO addPropertiesToSubcategory(Long subcategoryId, List<Long> propertiesIds) throws PropertiesNameGoodsNotFoundException {
//        SubcategoriesGoodsEntity subcategory = subcategoriesGoodsRepository.findById(subcategoryId).orElse(null);
//        List<PropertiesNameGoodsEntity> propertiesEntities = new ArrayList<>();
//        for (Long propertyId : propertiesIds) {
//            PropertiesNameGoodsEntity propertyEntity = propertiesNameGoodsRepository.findById(propertyId)
//                    .orElseThrow(() -> new PropertiesNameGoodsNotFoundException("Властивість товару не знайдено"));
//            propertiesEntities.add(propertyEntity);
//            subcategory.getPropertiesNameGoodsEntities().add(propertyEntity);
//            propertyEntity.getSubcategoriesGoodsEntities().add(subcategory);
//
//            subcategoriesGoodsRepository.save(subcategory);
//            propertiesNameGoodsRepository.save(propertyEntity);
//        }
//        return entityToDTO(subcategory);
//
//    }
    public SubcategoriesGoodsDTO addPropertiesToSubcategory(Long subcategoryId, List<Long> propertiesIds) throws PropertiesNameGoodsNotFoundException, SubcategoriesGoodsNotFoundException {
        SubcategoriesGoodsEntity subcategory = subcategoriesGoodsRepository.findById(subcategoryId)
                .orElseThrow(() -> new SubcategoriesGoodsNotFoundException("Підкатегорія не знайдена"));

        List<PropertiesNameGoodsEntity> propertiesEntities = new ArrayList<>();

        for (Long propertyId : propertiesIds) {
            PropertiesNameGoodsEntity propertyEntity = propertiesNameGoodsRepository.findById(propertyId)
                    .orElseThrow(() -> new PropertiesNameGoodsNotFoundException("Властивість товару не знайдено"));

            // Перевірка на існування такого зв'язку перед додаванням
            if (!subcategory.getPropertiesNameGoodsEntities().contains(propertyEntity)) {
                propertiesEntities.add(propertyEntity);
                subcategory.getPropertiesNameGoodsEntities().add(propertyEntity);
                propertyEntity.getSubcategoriesGoods().add(subcategory);

                subcategoriesGoodsRepository.save(subcategory);
                propertiesNameGoodsRepository.save(propertyEntity);
            } else {
                throw new DuplicateAssociationException("Зв'язок між підкатегорією та властивістю вже існує");
            }
        }
        //функція оновлення властивостей у вже доданих товарів
        return entityToDTO(subcategory);
    }

}