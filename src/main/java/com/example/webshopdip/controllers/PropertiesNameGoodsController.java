//package com.example.mayprojegtdip.controllers;
//
//import com.example.mayprojegtdip.entities.PropertiesGoodsEntity;
//import com.example.mayprojegtdip.entities.PropertiesNameGoodsEntity;
//import com.example.mayprojegtdip.exceptions.PropertiesGoodsNotFoundException;
//import com.example.mayprojegtdip.exceptions.PropertiesNameGoodsNotFoundException;
//import com.example.mayprojegtdip.repo.PropertiesGoodsRepository;
//import com.example.mayprojegtdip.repo.PropertiesNameGoodsRepository;
//import com.example.mayprojegtdip.services.PropertiesGoodsService;
//import com.example.mayprojegtdip.services.PropertiesNameGoodsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/propertiesNameGoods")
//public class PropertiesNameGoodsController {
//    @Autowired
//    private PropertiesNameGoodsService propertiesNameGoodsService;
//    @Autowired
//    private PropertiesNameGoodsRepository propertiesNameGoodsRepository;
//    @PostMapping
//    public ResponseEntity createPropertiesNameGoods(@RequestBody PropertiesNameGoodsEntity propertiesNameGoodsEntity){
//        try {
//            PropertiesNameGoodsEntity propertiesNameGoods = propertiesNameGoodsService.createPropertiesNameGoods(propertiesNameGoodsEntity);
//            return ResponseEntity.ok(propertiesNameGoods);
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//    @GetMapping("/getOne")
//    public ResponseEntity getOnePropertiesNameGoods(@RequestParam Long id){
//        try {
//            return ResponseEntity.ok(propertiesNameGoodsService.getOne(id));
//        }catch (PropertiesNameGoodsNotFoundException e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }
//    @GetMapping
//    public ResponseEntity<Iterable<PropertiesNameGoodsEntity>> getAll() {
//        Iterable<PropertiesNameGoodsEntity> propertiesNameGoodsEntities = propertiesNameGoodsRepository.findAll();
//        return new ResponseEntity<>(propertiesNameGoodsEntities, HttpStatus.OK);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePropertiesNameGoods(@PathVariable Long id) {
//        propertiesNameGoodsService.deletePropertiesNameGoods(id);
//        return ResponseEntity.noContent().build();
//    }
//}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.PropertiesNameGoodsDTO;
import com.example.webshopdip.dtos.PropertiesNameGoodsGetAllDTO;
import com.example.webshopdip.entities.PropertiesNameGoodsEntity;
import com.example.webshopdip.enums.ValueTypePropertiesEnum;
import com.example.webshopdip.exceptions.PropertiesNameGoodsNotFoundException;
import com.example.webshopdip.repositories.PropertiesNameGoodsRepository;
import com.example.webshopdip.services.PropertiesNameGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/propertiesNameGoods")
public class PropertiesNameGoodsController {

    @Autowired
    private PropertiesNameGoodsService propertiesNameGoodsService;
    @Autowired
    private PropertiesNameGoodsRepository propertiesNameGoodsRepository;

    @PostMapping
    public ResponseEntity<PropertiesNameGoodsDTO> createPropertiesNameGoods(@RequestBody PropertiesNameGoodsDTO dto) {
        try {
            System.out.println("createdDTO: getName"+ dto.getName());
            System.out.println("createdDTO getType: "+ dto.getValueType());
//            System.out.println("propertiesNameGoodsService: "+ propertiesNameGoodsService.createPropertiesNameGoods(dto).getName());
            PropertiesNameGoodsDTO createdDTO = propertiesNameGoodsService.createPropertiesNameGoods(dto);
            System.out.println("createdDTO: "+ createdDTO.getName());
            System.out.println("createdDTO: "+ createdDTO.getValueType());
            return ResponseEntity.ok(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new PropertiesNameGoodsDTO()); // or handle the error
        }
    }

    @GetMapping("/getOne")
    public ResponseEntity<PropertiesNameGoodsDTO> getOne(@RequestParam Long id) {
        try {
            PropertiesNameGoodsDTO propertiesNameGoodsDTO = propertiesNameGoodsService.getOne(id);
            List<ValueTypePropertiesEnum.ValueType> valueTypeList = Arrays.asList(ValueTypePropertiesEnum.ValueType.values());
            propertiesNameGoodsDTO.setValueTypeList(valueTypeList);

            return ResponseEntity.ok(propertiesNameGoodsDTO);
        } catch (PropertiesNameGoodsNotFoundException e) {
            return ResponseEntity.badRequest().body(new PropertiesNameGoodsDTO()); // or handle the error
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new PropertiesNameGoodsDTO()); // or handle the error
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<PropertiesNameGoodsGetAllDTO>> getAll() {
        Iterable<PropertiesNameGoodsEntity> entities = propertiesNameGoodsRepository.findAll();
        List<PropertiesNameGoodsGetAllDTO> dtos = new ArrayList<>();

        for (PropertiesNameGoodsEntity entity : entities) {
            dtos.add(propertiesNameGoodsService.entityToDTOGetAll(entity));
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropertiesNameGoods(@PathVariable Long id) {
        propertiesNameGoodsService.deletePropertiesNameGoods(id);
        return ResponseEntity.noContent().build();
    }
}


