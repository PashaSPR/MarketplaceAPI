//package com.example.mayprojegtdip.services;
//
//import com.example.mayprojegtdip.entities.CategoriesGoodsEntity;
//import com.example.mayprojegtdip.entities.CategoriesPropertiesEntity;
//import com.example.mayprojegtdip.entities.PropertiesNameGoodsEntity;
//import com.example.mayprojegtdip.entities.SubcategoriesGoodsEntity;
//import com.example.mayprojegtdip.exceptions.CategoriesPropertiesNotFoundException;
//import com.example.mayprojegtdip.repo.CategoriesPropertiesRepository;
//import jakarta.persistence.EntityNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CategoriesPropertiesService {
//    @Autowired
//    private CategoriesPropertiesRepository categoriesPropertiesRepository;
//    public CategoriesPropertiesEntity createCategoriesProperties(CategoriesPropertiesEntity categoriesPropertiesEntity){
//        return categoriesPropertiesRepository.save(categoriesPropertiesEntity);
//    }
//    public CategoriesPropertiesEntity createCategoriesPropertiesForGoods(SubcategoriesGoodsEntity subcategory, CategoriesGoodsEntity category) {
//        CategoriesPropertiesEntity propertiesEntity = new CategoriesPropertiesEntity();
//        propertiesEntity.setSubcategoriesGoods(subcategory);
//        // можливо, додати інші дані до propertiesEntity відповідно до вашої логіки
//        return categoriesPropertiesRepository.save(propertiesEntity);
//    }
//
////    public CategoriesPropertiesEntity createCategoriesPropertiesForGoods(SubcategoriesGoodsEntity subcategory, PropertiesNameGoodsEntity properties) {
////        CategoriesPropertiesEntity propertiesEntity = new CategoriesPropertiesEntity();
////        propertiesEntity.setSubcategoriesGoods(subcategory);
////        propertiesEntity.setPropertiesNameGoodsEntities(properties); // Додати цей рядок для встановлення властивості
////        // можливо, додати інші дані до propertiesEntity відповідно до вашої логіки
////        return categoriesPropertiesRepository.save(propertiesEntity);
////    }
//
////    public CategoriesPropertiesEntity createCategoriesProperties(Long subcategoryId, List<Long> propertyIds) {
////        SubcategoriesGoodsEntity subcategory = new SubcategoriesGoodsEntity();
////        subcategory.setId(subcategoryId);
////
////        List<PropertiesNameGoodsEntity> properties = new ArrayList<>();
////        for (Long propertyId : propertyIds) {
////            PropertiesNameGoodsEntity property = new PropertiesNameGoodsEntity();
////            property.setId(propertyId);
////            properties.add(property);
////        }
////
////        CategoriesPropertiesEntity propertiesEntity = new CategoriesPropertiesEntity();
////        propertiesEntity.setSubcategoriesGoods(subcategory);
////        propertiesEntity.setPropertiesNameGoodsEntities(properties);
////
////        return categoriesPropertiesRepository.save(propertiesEntity);
////    }
//
//    public CategoriesPropertiesEntity updateCategoriesPropertiesForGoods(Long propertiesId, SubcategoriesGoodsEntity subcategory, CategoriesGoodsEntity category) {
//        CategoriesPropertiesEntity propertiesEntity = categoriesPropertiesRepository.findById(propertiesId)
//                .orElseThrow(() -> new EntityNotFoundException("Properties not found"));
//
//        propertiesEntity.setSubcategoriesGoods(subcategory);
//        // можливо, виконати інші оновлення властивостей згідно з вашою логікою
//
//        return categoriesPropertiesRepository.save(propertiesEntity);
//    }
//
//
//
//    public CategoriesPropertiesEntity getOne(Long id) throws CategoriesPropertiesNotFoundException{
//        Optional<CategoriesPropertiesEntity> optional = categoriesPropertiesRepository.findById(id);
//        if (optional.isEmpty()) {
//            throw new CategoriesPropertiesNotFoundException("No record found");
//        }
//        return optional.get();
//    }
//    public void deleteCategoriesProperties(Long id) {
//        categoriesPropertiesRepository.deleteById(id);
//    }
//}
//
//
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.example.webshopdip.services;

import com.example.webshopdip.dtos.CategoriesPropertiesDTO;
import com.example.webshopdip.entities.CategoriesPropertiesEntity;
import com.example.webshopdip.exceptions.CategoriesPropertiesNotFoundException;
import com.example.webshopdip.exceptions.PropertiesNameGoodsNotFoundException;
import com.example.webshopdip.exceptions.SubcategoriesGoodsNotFoundException;
import com.example.webshopdip.repositories.CategoriesPropertiesRepository;
import com.example.webshopdip.repositories.PropertiesNameGoodsRepository;
import com.example.webshopdip.repositories.SubcategoriesGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoriesPropertiesService {

    @Autowired
    private CategoriesPropertiesRepository categoriesPropertiesRepository;
    @Autowired
    private SubcategoriesGoodsRepository subcategoriesGoodsRepository;
    @Autowired
    private PropertiesNameGoodsRepository propertiesNameGoodsRepository;

    public CategoriesPropertiesDTO createCategoriesProperties(CategoriesPropertiesDTO categoriesPropertiesDTO) throws SubcategoriesGoodsNotFoundException, PropertiesNameGoodsNotFoundException {
        CategoriesPropertiesEntity propertiesEntity = new CategoriesPropertiesEntity();

        propertiesEntity.setSubcategoriesGoods(subcategoriesGoodsRepository.findById(categoriesPropertiesDTO.getSubcategoriesGoodsId()).orElse(null));
        propertiesEntity.setPropertiesNameGoods(propertiesNameGoodsRepository.findById(categoriesPropertiesDTO.getPropertiesNameGoodsId()).orElse(null));

        return entityToDTO(propertiesEntity);
    }


    public CategoriesPropertiesDTO entityToDTO(CategoriesPropertiesEntity entity) {
        CategoriesPropertiesDTO dto = new CategoriesPropertiesDTO();
        dto.setId(entity.getId());
        dto.setSubcategoriesGoodsId(entity.getSubcategoriesGoods().getId());
        dto.setPropertiesNameGoodsId(entity.getPropertiesNameGoods().getId());

        return dto;
    }

    public CategoriesPropertiesDTO getOne(Long id) throws CategoriesPropertiesNotFoundException {
        Optional<CategoriesPropertiesEntity> optional = categoriesPropertiesRepository.findById(id);

        if (optional.isEmpty()) {
            throw new CategoriesPropertiesNotFoundException("No record found");
        }

        CategoriesPropertiesEntity entity = optional.get();
        return entityToDTO(entity);
    }

    public void deleteCategoriesProperties(Long id) {
        categoriesPropertiesRepository.deleteById(id);
    }

    // Other methods...
}
