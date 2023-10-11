package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.CategoriesGoodsDTO;
import com.example.webshopdip.dtos.GoodsGetAllDTO;
import com.example.webshopdip.entities.CategoriesGoodsEntity;
import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.exceptions.CategoriesGoodsNotFoundException;
import com.example.webshopdip.repositories.CategoriesGoodsRepository;
import com.example.webshopdip.services.CategoriesGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoriesGoods")
public class CategoriesGoodsController {
    @Autowired
    private CategoriesGoodsService categoriesGoodsService;
    @Autowired
    private CategoriesGoodsRepository categoriesGoodsRepository;

//    @PostMapping
//    public ResponseEntity newCategoriesGoods(@RequestBody CategoriesGoodsEntity categoriesGoods){
//        try {
//            categoriesGoodsService.categoriesGoodsRegistration(categoriesGoods);
//            return ResponseEntity.ok("Нова категорія товару успішно додана");
//        }
//        catch (CategoriesGoodsAlreadyExistException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//        catch (Exception e){
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }
@PostMapping
public ResponseEntity<CategoriesGoodsDTO> createCategoriesGoods(@RequestBody CategoriesGoodsDTO dto) {
    try {
        CategoriesGoodsDTO createdDTO = categoriesGoodsService.createCategoriesGoods(dto);
        return ResponseEntity.ok(createdDTO);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(new CategoriesGoodsDTO()); // or handle the error
    }
}
    @GetMapping("/getOne")
    public ResponseEntity getOneCategoriesGoods(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(categoriesGoodsService.getOne(id));
        } catch (CategoriesGoodsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Виникла помилка");
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<CategoriesGoodsEntity>> getAll() {
        Iterable<CategoriesGoodsEntity> categoriesGoodsEntities = categoriesGoodsRepository.findAll();
        return new ResponseEntity<>(categoriesGoodsEntities, HttpStatus.OK);
    }
}
