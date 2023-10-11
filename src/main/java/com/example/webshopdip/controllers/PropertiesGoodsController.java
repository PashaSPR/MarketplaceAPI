package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.PropertiesGoodsToSaveDTO;
import com.example.webshopdip.entities.PropertiesGoodsEntity;
import com.example.webshopdip.exceptions.PropertiesGoodsNotFoundException;
import com.example.webshopdip.repositories.PropertiesGoodsRepository;
import com.example.webshopdip.services.PropertiesGoodsService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propertiesGoods")
public class PropertiesGoodsController {
    @Autowired
    private PropertiesGoodsService propertiesGoodsService;
    @Autowired
    private PropertiesGoodsRepository propertiesGoodsRepository;
    @PostMapping
    public ResponseEntity<?> createPropertiesGoods(@RequestBody PropertiesGoodsToSaveDTO propertiesGoodsToSaveDTO) {
        try {
            PropertiesGoodsEntity propertiesGoods = propertiesGoodsService.createPropertiesGoods(propertiesGoodsToSaveDTO);
            return ResponseEntity.ok(propertiesGoods);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity<?> updatePropertiesGoods(@RequestBody PropertiesGoodsToSaveDTO propertiesGoodsToSaveDTO) {
        try {
            Long propertyId = propertiesGoodsToSaveDTO.getId();
            String newValue = propertiesGoodsToSaveDTO.getValue();

            // Отримуємо властивість за її ID з бази даних
            PropertiesGoodsEntity property = propertiesGoodsService.getOne(propertyId);

            // Перевіряємо, чи знайдена властивість і оновляємо її значення
            if (property != null) {
                property.setValue(newValue);
                propertiesGoodsRepository.save(property);
                return ResponseEntity.ok("Властивість оновлено успішно");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Помилка при оновленні властивості: " + e.getMessage());
        }
    }

    @GetMapping("/getOne")
    public ResponseEntity<?> getOnePropertiesGoods(@RequestParam Long id) {
        try {
            PropertiesGoodsEntity propertiesGoods = propertiesGoodsService.getOne(id);
            return ResponseEntity.ok(propertiesGoods);
        } catch (PropertiesGoodsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Виникла помилка");
        }
    }
    @GetMapping("/goods/{id}/properties")
    public ResponseEntity<List<PropertiesGoodsEntity>> getPropertiesByGoodsId(@PathVariable("id") Long goodsId, HttpServletRequest request) {
        return new ResponseEntity<>(propertiesGoodsService.getPropertiesByGoodsId(goodsId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<PropertiesGoodsEntity>> getAllPropertiesGoods() {
        Iterable<PropertiesGoodsEntity> propertiesGoodsEntities = propertiesGoodsRepository.findAll();
        return new ResponseEntity<>(propertiesGoodsEntities, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertiesGoods(@PathVariable Long id) {
        propertiesGoodsService.deletePropertiesGoods(id);
        return ResponseEntity.noContent().build();
    }
}
