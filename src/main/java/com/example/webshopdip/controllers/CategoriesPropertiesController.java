//package com.example.mayprojegtdip.controllers;
//
//import com.example.mayprojegtdip.entities.CategoriesPropertiesEntity;
//import com.example.mayprojegtdip.entities.PropertiesNameGoodsEntity;
//import com.example.mayprojegtdip.entities.SubcategoriesGoodsEntity;
//import com.example.mayprojegtdip.exceptions.CategoriesPropertiesNotFoundException;
//import com.example.mayprojegtdip.repo.CategoriesPropertiesRepository;
//import com.example.mayprojegtdip.repo.PropertiesNameGoodsRepository;
//import com.example.mayprojegtdip.services.CategoriesPropertiesService;
//import com.example.mayprojegtdip.services.PropertiesNameGoodsService;
//import com.example.mayprojegtdip.services.SubcategoriesGoodsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/categoriesProperties")
//public class CategoriesPropertiesController {
//    @Autowired
//    private CategoriesPropertiesService categoriesPropertiesService;
//    @Autowired
//    private SubcategoriesGoodsService subcategoriesGoodsService;
//    @Autowired
//    private PropertiesNameGoodsService propertiesNameGoodsService;
//    @Autowired
//    private CategoriesPropertiesRepository categoriesPropertiesRepository;
//    @Autowired
//    private PropertiesNameGoodsRepository propertiesNameGoodsRepository;
//    @PostMapping
//    public ResponseEntity createCategoriesProperties(@RequestBody CategoriesPropertiesEntity categoriesPropertiesEntity) {
//        try {
//            CategoriesPropertiesEntity categoriesProperties = categoriesPropertiesService.createCategoriesProperties(categoriesPropertiesEntity);
//            return ResponseEntity.ok(categoriesProperties);
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
////    @PostMapping("/{id}/addProperties")
////    public ResponseEntity addPropertiesToSubcategory(@PathVariable Long id, @RequestBody List<PropertiesNameGoodsEntity> properties) {
////        CategoriesPropertiesEntity categoriesPropertiesEntity = new CategoriesPropertiesEntity();
////        try {
//////            System.out.println("Received properties from frontend: " + properties);
////            SubcategoriesGoodsEntity subcategory = subcategoriesGoodsService.getOne(id);
////            categoriesPropertiesEntity.setSubcategoriesGoods(subcategory);
////            List<PropertiesNameGoodsEntity> ropertiesListForSubcategories = new ArrayList<>();
////            System.out.println("Received categoriesPropertiesEntity: " + categoriesPropertiesEntity.getSubcategoriesGoods().getId());
////            for (PropertiesNameGoodsEntity property : properties) {
////                ropertiesListForSubcategories.add(propertiesNameGoodsRepository.getOne(property.getId()));
////                categoriesPropertiesEntity = categoriesPropertiesService.createCategoriesProperties(categoriesPropertiesService.getOne(property.getId()));
////                System.out.println("Received properties getId: " + property.getId());
////            }
////            categoriesPropertiesEntity.getPropertiesNameGoodsEntities().addAll(properties);
////            categoriesPropertiesRepository.save(categoriesPropertiesEntity);
////
////            return ResponseEntity.ok("Вибрані властивості додано успішно");
////        } catch (Exception e) {
////            return ResponseEntity.badRequest().body("Виникла помилка");
////        }
////    }
//
//
//    @PostMapping("/{id}/addProperties")
//    public ResponseEntity addPropertiesToSubcategory(@PathVariable Long id, @RequestBody List<Long> propertiesId) {
//        try {
//            SubcategoriesGoodsEntity subcategory = subcategoriesGoodsService.getOne(id);
////            List<CategoriesPropertiesEntity> newCategoriesPropertiesList=new ArrayList<>();
//
//            for (Long propertyId : propertiesId) {
//                CategoriesPropertiesEntity newCategoriesProperties = new CategoriesPropertiesEntity();
//                newCategoriesProperties.setSubcategoriesGoods(subcategory);
//                newCategoriesProperties.setPropertiesNameGoods(propertiesNameGoodsService.getOne(propertyId));
////                newCategoriesPropertiesList.add(newCategoriesProperties);
//                categoriesPropertiesRepository.save(newCategoriesProperties);
//            }
//
//
//
//
////            CategoriesPropertiesEntity categoriesPropertiesEntity = new CategoriesPropertiesEntity();
////            categoriesPropertiesEntity.setSubcategoriesGoods(subcategory);
////
////            List<PropertiesNameGoodsEntity> propertiesForSubcategory = propertiesNameGoodsRepository.findAllById(propertyIds);
//////            categoriesPropertiesEntity.getPropertiesNameGoodsEntities().addAll(propertiesForSubcategory);
//////            categoriesPropertiesEntity.setPropertiesNameGoodsEntities(propertiesForSubcategory);
//
//
////            List<CategoriesPropertiesEntity> categoriesPropertiesEntityList = new
////            for (CategoriesPropertiesEntity property : addCategoriesProperties) {
////                System.out.println("Received properties getId: " + property.getSubcategoriesGoods());
////                System.out.println("Received properties getId: " + property.getPropertiesNameGoods());
////            }
////            categoriesPropertiesRepository.save(addCategoriesProperties);
//
//            return ResponseEntity.ok("Вибрані властивості додано успішно");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }
//
//
//
////    @PostMapping("/{id}/addProperties")
////    public ResponseEntity addPropertiesToSubcategory(@PathVariable Long id, @RequestBody List<Long> propertyIds) {
////        try {
////            SubcategoriesGoodsEntity subcategory = subcategoriesGoodsService.getOne(id);
////
////            CategoriesPropertiesEntity categoriesPropertiesEntity = new CategoriesPropertiesEntity();
////            categoriesPropertiesEntity.setSubcategoriesGoods(subcategory);
////
////            List<PropertiesNameGoodsEntity> propertiesListForSubcategories = new ArrayList<>();
////            for (Long propertyId : propertyIds) {
////                PropertiesNameGoodsEntity property = propertiesNameGoodsRepository.findById(propertyId).orElse(null);
////                if (property != null) {
////                    propertiesListForSubcategories.add(property);
////                }
////            }
////
////            categoriesPropertiesEntity.getPropertiesNameGoodsEntities().addAll(propertiesListForSubcategories);
////            categoriesPropertiesRepository.save(categoriesPropertiesEntity);
////
////            return ResponseEntity.ok("Вибрані властивості додано успішно");
////        } catch (Exception e) {
////            return ResponseEntity.badRequest().body("Виникла помилка");
////        }
////    }
//
//
////    @PostMapping
////    public ResponseEntity createCategoriesProperties(@RequestBody List<CategoriesPropertiesEntity> propertiesList) {
////        try {
////            List<CategoriesPropertiesEntity> savedProperties = new ArrayList<>();
////
////            for (CategoriesPropertiesEntity propertiesEntity : propertiesList) {
////                CategoriesPropertiesEntity savedProperty = categoriesPropertiesService.createCategoriesProperties(propertiesEntity);
////                savedProperties.add(savedProperty);
////            }
////
////            return ResponseEntity.ok(savedProperties);
////        } catch (Exception e) {
////            return ResponseEntity.badRequest().body(e.getMessage());
////        }
////    }
//
////    @PostMapping
////    public ResponseEntity createCategoriesProperties(@RequestBody List<CategoriesPropertiesEntity> categoriesPropertiesEntities) {
////        try {
////            // перевіряємо що приходить з frontend
////            System.out.println("Received properties from frontend: " + categoriesPropertiesEntities);
////
//////            // Логування докладної інформації про кожен об'єкт
//////            for (CategoriesPropertiesEntity entity : categoriesPropertiesEntities) {
//////                System.out.println("Received property from frontend - id: " + entity.getId() +
//////                        ", subcategoryId: " + entity.getSubcategoriesGoods().getId() +
//////                        ", propertyId: " + entity.getPropertiesNameGoods().getId());
//////            }
//////            List<CategoriesPropertiesEntity> createdProperties = new ArrayList<>();
//////            for (CategoriesPropertiesEntity entity : categoriesPropertiesEntities) {
//////                CategoriesPropertiesEntity categoriesProperties = categoriesPropertiesService.createCategoriesProperties(entity);
//////                createdProperties.add(categoriesProperties);
//////            }
////            List<CategoriesPropertiesEntity> createdProperties = new ArrayList<>();
////            for (CategoriesPropertiesEntity entity : categoriesPropertiesEntities) {
////                SubcategoriesGoodsEntity subcategory = entity.getSubcategoriesGoods();
////                PropertiesNameGoodsEntity properties = entity.getPropertiesNameGoodsEntities(); // Додати цей рядок для отримання властивості properties
////                CategoriesPropertiesEntity categoriesProperties = categoriesPropertiesService.createCategoriesPropertiesForGoods(subcategory, properties);
////                createdProperties.add(categoriesProperties);
////            }
////
////            return ResponseEntity.ok(createdProperties);
////        } catch (Exception e){
////            return ResponseEntity.badRequest().body(e.getMessage());
////        }
////    }
//
////
////    @PostMapping
////    public ResponseEntity createCategoriesProperties(@RequestBody Map<String, Object> requestBody) {
////        try {
////            Long subcategoryId = (Long) requestBody.get("subcategoryId");
////            List<Long> propertyIds = (List<Long>) requestBody.get("propertyIds");
////
////            CategoriesPropertiesEntity createdProperties = categoriesPropertiesService.createCategoriesProperties(subcategoryId, propertyIds);
////            return ResponseEntity.ok(createdProperties);
////        } catch (Exception e) {
////            return ResponseEntity.badRequest().body(e.getMessage());
////        }
////    }
//    @GetMapping("/getOne")
//    public ResponseEntity getOneCategoriesProperties(@RequestParam Long id){
//        try {
//            return ResponseEntity.ok(categoriesPropertiesService.getOne(id));
//        }catch (CategoriesPropertiesNotFoundException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }
//    @GetMapping
//    public ResponseEntity<Iterable<CategoriesPropertiesEntity>> getAll() {
//        Iterable<CategoriesPropertiesEntity> categoriesPropertiesEntities = categoriesPropertiesRepository.findAll();
//        return new ResponseEntity<>(categoriesPropertiesEntities, HttpStatus.OK);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCategoriesProperties(@PathVariable Long id) {
//        categoriesPropertiesService.deleteCategoriesProperties(id);
//        return ResponseEntity.noContent().build();
//    }
//}
//
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////


package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.CategoriesPropertiesDTO;
import com.example.webshopdip.exceptions.CategoriesPropertiesNotFoundException;
import com.example.webshopdip.services.CategoriesPropertiesService;
import com.example.webshopdip.services.PropertiesNameGoodsService;
import com.example.webshopdip.services.SubcategoriesGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categoriesProperties")
public class CategoriesPropertiesController {

    @Autowired
    private CategoriesPropertiesService categoriesPropertiesService;
    @Autowired
    private SubcategoriesGoodsService subcategoriesGoodsService;
    @Autowired
    private PropertiesNameGoodsService propertiesNameGoodsService;

    @PostMapping
    public ResponseEntity<CategoriesPropertiesDTO> createCategoriesProperties(@RequestBody CategoriesPropertiesDTO categoriesPropertiesDTO) {
        try {
            CategoriesPropertiesDTO createdDTO = categoriesPropertiesService.createCategoriesProperties(categoriesPropertiesDTO);
            return ResponseEntity.ok(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CategoriesPropertiesDTO()); // or handle the error
        }
    }
//    private CategoriesPropertiesEntity dtoToEntity(CategoriesPropertiesDTO dto) {
//        CategoriesPropertiesEntity entity = new CategoriesPropertiesEntity();
//        entity.setId(dto.getId());
//        entity.setSubcategoriesGoods(dto.getSubcategoriesGoodsId());
//        entity.setPropertiesNameGoods(dto.getPropertiesNameGoods_id());
//        return entity;
//    }
//
//    private CategoriesPropertiesDTO entityToDTO(CategoriesPropertiesEntity entity) {
//        CategoriesPropertiesDTO dto = new CategoriesPropertiesDTO();
//        dto.setId(entity.getId());
//        dto.setSubcategoriesGoods_id(entity.getSubcategoriesGoods());
//        dto.setPropertiesNameGoods_id(entity.getPropertiesNameGoods());
//        return dto;
//    }

    @PostMapping("/{id}/addProperties")
    public ResponseEntity<String> addPropertiesToSubcategory(@PathVariable Long id, @RequestBody List<Long> propertiesId) {
        try {
            for (Long propertyId : propertiesId) {
                CategoriesPropertiesDTO dto = new CategoriesPropertiesDTO();
                dto.setSubcategoriesGoodsId(subcategoriesGoodsService.getOne(id).getId());
                dto.setPropertiesNameGoodsId(propertiesNameGoodsService.getOne(propertyId).getId()); // Виправлено

                categoriesPropertiesService.createCategoriesProperties(dto);
            }

            return ResponseEntity.ok("Вибрані властивості додано успішно");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Виникла помилка");
        }
    }



    @GetMapping("/getOne")
    public ResponseEntity<CategoriesPropertiesDTO> getOneCategoriesProperties(@RequestParam Long id) {
        try {
            CategoriesPropertiesDTO dto = categoriesPropertiesService.getOne(id);
            return ResponseEntity.ok(dto);
        } catch (CategoriesPropertiesNotFoundException e) {
            return ResponseEntity.badRequest().body(new CategoriesPropertiesDTO()); // or handle the error
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CategoriesPropertiesDTO()); // or handle the error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriesProperties(@PathVariable Long id) {
        categoriesPropertiesService.deleteCategoriesProperties(id);
        return ResponseEntity.noContent().build();
    }

}
