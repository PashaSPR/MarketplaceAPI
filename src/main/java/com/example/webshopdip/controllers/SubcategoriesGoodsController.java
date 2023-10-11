//package com.example.mayprojegtdip.controllers;
//
//import com.example.mayprojegtdip.dto.SubcategoriesGoodsDTO;
//import com.example.mayprojegtdip.entities.PropertiesNameGoodsEntity;
//import com.example.mayprojegtdip.entities.SubcategoriesGoodsEntity;
//import com.example.mayprojegtdip.exceptions.SubcategoriesGoodsAlreadyExistException;
//import com.example.mayprojegtdip.exceptions.SubcategoriesGoodsNotFoundException;
//import com.example.mayprojegtdip.repo.SubcategoriesGoodsRepository;
//import com.example.mayprojegtdip.services.SubcategoriesGoodsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/subcategoriesGoods")
//public class SubcategoriesGoodsController {
//    @Autowired
//    SubcategoriesGoodsService subcategoriesGoodsService;
//    @Autowired
//    SubcategoriesGoodsRepository subcategoriesGoodsRepository;
//
//    @PostMapping
//    public ResponseEntity newSubcategoriesGoods(@RequestBody SubcategoriesGoodsEntity subcategoriesGoods) {
//        try {
//            subcategoriesGoodsService.subcategoriesGoodsRegistration(subcategoriesGoods);
//            return ResponseEntity.ok("Нову підкатегорію додано");
//        } catch (SubcategoriesGoodsAlreadyExistException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }
//
//
////    @PostMapping
////    public ResponseEntity<SubcategoriesGoodsEntity> createSubcategoriesGoods(@RequestBody SubcategoriesGoodsEntity subcategoriesGoodsEntity,
////                                                                             @RequestParam("categoriesGoodsId") Long categoriesGoodsId) {
////        try {
////            SubcategoriesGoodsEntity createdSubcategoriesGoods = subcategoriesGoodsService.createSubcategoriesGoods(subcategoriesGoodsEntity, categoriesGoodsId);
////            return ResponseEntity.ok(createdSubcategoriesGoods);
////        } catch (CategoriesGoodsNotFoundException e) {
////            return ResponseEntity.badRequest().body(null); // Можна повернути відповідний JSON-об'єкт або повідомлення про помилку
////        }
////    }
//
//
////    @PostMapping
////    public ResponseEntity createSubcategoriesGoods(@RequestBody SubcategoriesGoodsEntity subcategoriesGoods,
////                                                   @RequestParam Long categoriesGoodsId){
////        try {
////            subcategoriesGoodsService.createSubcategoriesGoods(subcategoriesGoods, categoriesGoodsId);
////            return ResponseEntity.ok("Нову підкатегорію додано 2");
////        }
//////        catch (SubcategoriesGoodsAlreadyExistException e){
//////            return ResponseEntity.badRequest().body(e.getMessage());
//////        }
////        catch (Exception e){
////            return ResponseEntity.badRequest().body("Виникла помилка");
////        }
////    }
//
////    @PostMapping
////    public ResponseEntity newSubcategoriesGoods(@RequestBody SubcategoriesGoodsEntity subcategoriesGoods,
////                                                @RequestParam("categoriesGoods_id") Long categoriesGoodsId) {
////        try {
////            subcategoriesGoodsService.createSubcategoriesGoods(subcategoriesGoods, categoriesGoodsId);
////            return ResponseEntity.ok("Нова підкатегорія товару успішно додана");
////        }
//////        catch (CategoriesGoodsNotFoundException e) {
//////            return ResponseEntity.badRequest().body(e.getMessage());
//////        }
////        catch (Exception e) {
////            return ResponseEntity.badRequest().body("Виникла помилка");
////        }
////    }
//
//
//    @PostMapping("/{id}/addProperties")
//    public ResponseEntity addPropertiesToSubcategory(@PathVariable Long id, @RequestBody List<PropertiesNameGoodsEntity> properties) {
//        try {
//            SubcategoriesGoodsEntity subcategory = subcategoriesGoodsService.getOne(id);
//            for (PropertiesNameGoodsEntity property : properties) {
//                property.setSubcategoriesGoods(subcategory);
//            }
//            subcategory.getPropertiesNameGoodsEntities().addAll(properties);
//            subcategoriesGoodsRepository.save(subcategory);
//
//            return ResponseEntity.ok("Вибрані властивості додано успішно");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }
//    @GetMapping("/getOne")
//    public ResponseEntity getOneSubcategoriesGoods(@RequestParam Long id) {
//        try {
//            System.out.println("SubcategoriesGoods size(): " + subcategoriesGoodsService.getOne(id).getPropertiesNameGoodsEntities().size());
//            return ResponseEntity.ok(subcategoriesGoodsService.getOne(id));
//        } catch (SubcategoriesGoodsNotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }
//
//
////    @GetMapping("/getOne")
////    public ResponseEntity getOneSubcategoriesGoods(@RequestParam Long id) {
////        try {
////            SubcategoriesGoodsEntity entity = subcategoriesGoodsService.getOne(id);
////            SubcategoriesGoodsDTO dto = subcategoriesGoodsService.entityToDTO(entity);
////            return ResponseEntity.ok(dto);
////        } catch (SubcategoriesGoodsNotFoundException e) {
////            return ResponseEntity.badRequest().body(e.getMessage());
////        } catch (Exception e) {
////            return ResponseEntity.badRequest().body("Виникла помилка");
////        }
////    }
//    @GetMapping
//    public ResponseEntity<Iterable<SubcategoriesGoodsEntity>> getAll() {
//        Iterable<SubcategoriesGoodsEntity> subcategoriesGoodsEntities = subcategoriesGoodsRepository.findAll();
//        return new ResponseEntity<>(subcategoriesGoodsEntities, HttpStatus.OK);
//    }
//
//    @GetMapping("/getOneWithProperties")
//    public ResponseEntity getOneSubcategoriesGoodsWithProperties(@RequestParam Long id) {
//        try {
//            SubcategoriesGoodsEntity subcategory = subcategoriesGoodsService.getOneWithProperties(id);
//            return ResponseEntity.ok(subcategory);
//        } catch (SubcategoriesGoodsNotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }
//}
//
//
//
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.SubcategoriesForCatalogDTO;
import com.example.webshopdip.dtos.SubcategoriesGoodsDTO;
import com.example.webshopdip.entities.SubcategoriesGoodsEntity;
import com.example.webshopdip.exceptions.SubcategoriesGoodsNotFoundException;
import com.example.webshopdip.repositories.SubcategoriesGoodsRepository;
import com.example.webshopdip.services.SubcategoriesGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subcategoriesGoods")
public class SubcategoriesGoodsController {

    @Autowired
    private SubcategoriesGoodsService subcategoriesGoodsService;
    @Autowired
    private SubcategoriesGoodsRepository subcategoriesGoodsRepository;

    @PostMapping
    public ResponseEntity<SubcategoriesGoodsDTO> createSubcategoriesGoods(@RequestBody SubcategoriesGoodsDTO dto) {
        try {
            SubcategoriesGoodsDTO createdDTO = subcategoriesGoodsService.createSubcategoriesGoods(dto);
            return ResponseEntity.ok(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new SubcategoriesGoodsDTO()); // or handle the error
        }
    }

    //    @PostMapping("/{id}/addProperties")
//    public ResponseEntity<String> addPropertiesToSubcategory(@PathVariable Long id, @RequestBody List<Long> propertiesIds) {
//        try {
//            SubcategoriesGoodsDTO dto = subcategoriesGoodsService.addPropertiesToSubcategory(id, propertiesIds);
//            return ResponseEntity.ok("Вибрані властивості додано успішно");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }
//
//    @PostMapping("/{subcategoryId}/addProperty")
//    public ResponseEntity<SubcategoriesGoodsDTO> addPropertyToSubcategory(
//            @PathVariable Long subcategoryId,
//            @RequestParam Long propertyId) {
//        try {
//            SubcategoriesGoodsDTO updatedDTO = subcategoriesGoodsService.addPropertyToSubcategory(subcategoryId, propertyId);
//            if (updatedDTO != null) {
//                return ResponseEntity.ok(updatedDTO);
//            } else {
//                return ResponseEntity.notFound().build(); // Сутності не знайдені
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new SubcategoriesGoodsDTO()); // або обробка помилки
//        }
//    }
    @PostMapping("/{subcategoryId}/addProperties")
    public ResponseEntity<SubcategoriesGoodsDTO> addPropertiesToSubcategory(@PathVariable Long subcategoryId, @RequestBody List<Long> propertiesIds) {
//        System.out.println("subcategoryId: " + subcategoryId);
//        for (Long propId : propertiesIds) {
//            System.out.println("propertyIds: " + propId);
//        }
        try {
            SubcategoriesGoodsDTO updatedDTO = subcategoriesGoodsService.addPropertiesToSubcategory(subcategoryId, propertiesIds);
            if (updatedDTO != null) {
                return ResponseEntity.ok(updatedDTO);
            } else {
                return ResponseEntity.notFound().build(); // Сутності не знайдені
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new SubcategoriesGoodsDTO()); // або обробка помилки
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<SubcategoriesGoodsDTO>> getAll() {
        Iterable<SubcategoriesGoodsEntity> subcategoriesGoodsEntities = subcategoriesGoodsRepository.findAll();
        List<SubcategoriesGoodsDTO> subcategoriesGoodsDTOS = new ArrayList<>();
        for (SubcategoriesGoodsEntity subcategoriesGoodsEntity : subcategoriesGoodsEntities) {
            SubcategoriesGoodsDTO dto = subcategoriesGoodsService.entityToDTO(subcategoriesGoodsEntity);
            subcategoriesGoodsDTOS.add(dto);
//            System.out.println("subcategoriesGoodsDTO: " + dto.getName());
        }
        return new ResponseEntity<>(subcategoriesGoodsDTOS, HttpStatus.OK);
    }

    @GetMapping("/getOne")
    public ResponseEntity<SubcategoriesGoodsDTO> getOneSubcategoriesGoods(@RequestParam Long id) {
//        System.out.println("id: " + id);
        try {
            SubcategoriesGoodsDTO dto = subcategoriesGoodsService.getOne(id);
            return ResponseEntity.ok(dto);
        } catch (SubcategoriesGoodsNotFoundException e) {
            return ResponseEntity.badRequest().body(new SubcategoriesGoodsDTO()); // or handle the error
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new SubcategoriesGoodsDTO()); // or handle the error
        }
    }

//    @GetMapping("/getByGoodsId")
//    public ResponseEntity<SubcategoriesGoodsDTO> getByGoodsId(@RequestParam Long id) {
////        System.out.println("id: " + id);
//        try {
//            SubcategoriesGoodsDTO dto = subcategoriesGoodsService.getOne(id);
//            return ResponseEntity.ok(dto);
//        } catch (SubcategoriesGoodsNotFoundException e) {
//            return ResponseEntity.badRequest().body(new SubcategoriesGoodsDTO()); // or handle the error
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new SubcategoriesGoodsDTO()); // or handle the error
//        }
//    }

    @GetMapping("/getByCategoriesId")
    public ResponseEntity<Iterable<SubcategoriesGoodsDTO>> getByCategoriesId(@RequestParam Long id) {
//        System.out.println("id: " + id);
        try {
            List<SubcategoriesGoodsDTO> dtos = subcategoriesGoodsService.getByCategoryId(id);
            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ArrayList<>() {
            }); // or handle the error
        }
    }


    @GetMapping("/getOneWithProperties")
    public ResponseEntity<SubcategoriesGoodsDTO> getOneSubcategoriesGoodsWithProperties(@RequestParam Long id) {
        try {
            SubcategoriesGoodsDTO dto = subcategoriesGoodsService.getOne(id);
            return ResponseEntity.ok(dto);
        } catch (SubcategoriesGoodsNotFoundException e) {
            return ResponseEntity.badRequest().body(new SubcategoriesGoodsDTO()); // or handle the error
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new SubcategoriesGoodsDTO()); // or handle the error
        }
    }

    @GetMapping("/getGoodsByCategoriesId")
    public ResponseEntity<Iterable<SubcategoriesForCatalogDTO>> getGoodsByCategoriesId(@RequestParam Long id) {
//        System.out.println("id: " + id);
        try {
            List<SubcategoriesForCatalogDTO> dtos = subcategoriesGoodsService.getGoodsByCategoriesId(id);
            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ArrayList<>() {
            }); // or handle the error
        }
    }
}
