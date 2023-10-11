package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.*;
import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.entities.GoodsInvoicesEntity;
import com.example.webshopdip.entities.PropertiesGoodsEntity;
import com.example.webshopdip.entities.PropertiesNameGoodsEntity;
import com.example.webshopdip.repositories.GoodsInvoicesRepository;
import com.example.webshopdip.repositories.GoodsRepository;
import com.example.webshopdip.services.GoodsInvoicesService;
import com.example.webshopdip.services.GoodsOrdersService;
import com.example.webshopdip.services.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goodsOrders")
public class GoodsOrdersController {

    @Autowired
    private GoodsOrdersService goodsOrdersService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsInvoicesService goodsInvoicesService;
    @Autowired
    private GoodsInvoicesRepository goodsInvoicesRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @PostMapping
    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(@RequestBody GoodsOrdersDTO goodsOrdersDTO) {

        try {
            Optional<GoodsEntity> goodsEntityOptional = goodsRepository.findById(goodsOrdersDTO.getGoodsInvoicesDTO().getGoods().getId());

            if (goodsEntityOptional.isPresent()) {
                GoodsEntity goodsEntity = goodsEntityOptional.get();

                GoodsInvoicesDTO goodsInvoicesDTO = goodsInvoicesService.getOne(goodsOrdersDTO.getGoodsInvoicesDTO().getId());
                goodsInvoicesDTO.setGoods(goodsService.entityToDTO(goodsEntity));

                goodsOrdersDTO.setGoodsInvoicesDTO(goodsInvoicesDTO);

//                GoodsOrdersDTO createdDTO = goodsOrdersService.createGoodsOrders(goodsOrdersDTO);
//                return ResponseEntity.ok(createdDTO);
                return ResponseEntity.ok(goodsOrdersDTO);
            } else {
                return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити випадок, коли товар не знайдено
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити помилку
        }
    }
    @GetMapping
    public ResponseEntity<List<GoodsOrdersDTO>> getAll(HttpServletRequest request) {
        List<GoodsOrdersDTO> goodsOrdersDTOS = goodsOrdersService.getAll(request);
        return new ResponseEntity<>(goodsOrdersDTOS, HttpStatus.OK);
    }

    @GetMapping("/getOne")
    public ResponseEntity<GoodsOrdersDTO> getOneGoodsOrders(@RequestParam Long id, HttpServletRequest request) {
//        System.out.println("id: " + id);
        try {
            GoodsOrdersDTO dto = goodsOrdersService.getOne(id);

//            GoodsInvoicesDTO goodsInvoicesDTO = goodsInvoicesService.getOne(dto.getGoodsInvoicesDTO().getId());
//            goodsInvoicesDTO
            String currentUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            List<PhotosGoodsDTO> photos = dto.getGoodsInvoicesDTO().getGoods().getPhotosGoodsDTOS();
            for (PhotosGoodsDTO photo : photos) {
                String imagePath = currentUrl + "/images/" + photo.getPath();
                photo.setPath(imagePath);
            }

            GoodsInvoicesEntity entity = goodsInvoicesRepository.findById(id).orElse(new GoodsInvoicesEntity());

            System.out.println("PropertiesGoods.size: " + entity.getGoods().getPropertiesGoods().size());
            List<PropertiesGoodsEntity> propertiesGoodsList = entity.getGoods().getPropertiesGoods();
            List<PropertiesGoodsDTO> propertiesDTOList = new ArrayList<>();
            for (PropertiesGoodsEntity propertiesGoods : propertiesGoodsList) {
                PropertiesNameGoodsEntity propertiesNameGoods = propertiesGoods.getPropertiesNameGoods();
                PropertiesGoodsDTO propertiesGoodsDTO = new PropertiesGoodsDTO();
                propertiesGoodsDTO.setId(propertiesGoods.getId());
                propertiesGoodsDTO.setValue(propertiesGoods.getValue());
                propertiesGoodsDTO.setPropertiesName(propertiesNameGoods.getName()); // Додаємо назву властивості
                propertiesGoodsDTO.setType(propertiesNameGoods.getValueType()); // Додаємо тип значення властивості
                propertiesDTOList.add(propertiesGoodsDTO);
            }
            dto.getGoodsInvoicesDTO().getGoods().setPropertiesGoodsDTOS(propertiesDTOList);

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // or handle the error
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteGoodsOrders(@PathVariable Long id) {
        try {

            goodsOrdersService.delete(id);

            return ResponseEntity.ok("Замовлення успішно видалено");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
