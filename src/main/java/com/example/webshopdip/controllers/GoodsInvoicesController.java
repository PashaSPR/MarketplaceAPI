package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.*;
import com.example.webshopdip.entities.*;
import com.example.webshopdip.repositories.GoodsRepository;
import com.example.webshopdip.repositories.GoodsInvoicesRepository;
import com.example.webshopdip.services.GoodsService;
import com.example.webshopdip.services.GoodsInvoicesService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goodsinvoices")//МЕТОД ВИДАЛЕННЯ ДАНИХ
public class GoodsInvoicesController {
    @Autowired
    private GoodsInvoicesService goodsInvoicesService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsInvoicesRepository goodsInvoicesRepository;
    @Autowired
    private GoodsRepository goodsRepository;


    // ////////////////////////2023.09.07/////////////////////////////////////////////////////////////////////
//    @PostMapping
//    public ResponseEntity<GoodsInvoicesDTO> createGoodsInvoices(@RequestBody GoodsInvoicesDTO goodsInvoicesDTO) {
//        System.out.println("goodsInvoicesDTO: " + goodsInvoicesDTO);
//        System.out.println("getPrice: " + goodsInvoicesDTO.getPrice());
//        System.out.println("getGoods: " + goodsInvoicesDTO.getGoods());
//        System.out.println("getQuantity: " + goodsInvoicesDTO.getQuantity());
//
//
//        try {
//            GoodsInvoicesDTO createdDTO = goodsInvoicesService.createGoodsInvoices(goodsInvoicesDTO);
//            return ResponseEntity.ok(createdDTO);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new GoodsInvoicesDTO()); // or handle the error
//        }
//    }

    @PostMapping
    public ResponseEntity<GoodsInvoicesDTO> createGoodsInvoices(@RequestBody GoodsInvoicesToSaveDTO goodsInvoicesToSaveDTO) {
//        System.out.println("goodsInvoicesDTO: " + goodsInvoicesToSaveDTO);
//        System.out.println("getPrice: " + goodsInvoicesToSaveDTO.getPrice());
//        System.out.println("getGoods: " + goodsInvoicesToSaveDTO.getGoodsId());
//        System.out.println("getQuantity: " + goodsInvoicesToSaveDTO.getQuantity());
        try {
            Optional<GoodsEntity> goodsEntityOptional = goodsRepository.findById(goodsInvoicesToSaveDTO.getGoodsId());

            if (goodsEntityOptional.isPresent()) {
                GoodsEntity goodsEntity = goodsEntityOptional.get();
                goodsInvoicesToSaveDTO.setGoodsId(goodsService.entityToDTO(goodsEntity).getId());
                GoodsInvoicesDTO createdDTO = goodsInvoicesService.createGoodsInvoices(goodsInvoicesToSaveDTO);
                return ResponseEntity.ok(createdDTO);
            } else {
                return ResponseEntity.badRequest().body(new GoodsInvoicesDTO()); // Обробити випадок, коли товар не знайдено
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GoodsInvoicesDTO()); // Обробити помилку
        }
    }

    //    @GetMapping
//    public ResponseEntity<Iterable<GoodsInvoicesDTO>> getAll(HttpServletRequest request) {
//        Iterable<GoodsInvoicesEntity> goodsInvoicesEntities = goodsInvoicesRepository.findAll();
//        List<GoodsInvoicesDTO> goodsInvoicesDTOS = new ArrayList<>();
//        for (GoodsInvoicesEntity goodsInvoicesEntity : goodsInvoicesEntities) {
//            GoodsInvoicesDTO dto = goodsInvoicesService.entityToDTO(goodsInvoicesEntity);
//            goodsInvoicesDTOS.add(dto);
//        }
//
//        String currentUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//
//        for (GoodsInvoicesDTO dto : goodsInvoicesDTOS) {
//            List<PhotosGoodsDTO> photosDTOList = dto.getGoods().getPhotosGoodsDTOS();
//
//            for (PhotosGoodsDTO photoDTO : photosDTOList) {
//                String imagePath = currentUrl + "/images/" + photoDTO.getPath();
//                photoDTO.setPath(imagePath);
////                System.out.println("Image path: " + imagePath);
//            }
//        }
//        GoodsInvoicesDTO goodsInvoicesDTOS = goodsInvoicesService.getAll(request);
//
//        return new ResponseEntity<?>(goodsInvoicesDTOS, HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<List<GoodsInvoicesDTO>> getAll(HttpServletRequest request) {
        List<GoodsInvoicesDTO> goodsInvoicesDTOS = goodsInvoicesService.getAll(request);
        return new ResponseEntity<>(goodsInvoicesDTOS, HttpStatus.OK);
    }


    public ResponseEntity<Iterable<GoodsInvoicesDTO>> getForCategory(
            HttpServletRequest request,
            @RequestParam(name = "category", required = false) String category
    ) {
        List<GoodsInvoicesDTO> goodsInvoicesDTOS = goodsInvoicesService.getFindByCategoriesGoodsName(request, category);
        return new ResponseEntity<>(goodsInvoicesDTOS, HttpStatus.OK);
    }

    public ResponseEntity<Iterable<GoodsInvoicesDTO>> getForSubcategory(
            HttpServletRequest request,
            @RequestParam(name = "subcategory", required = false) String subcategory
    ) {
        List<GoodsInvoicesDTO> goodsInvoicesDTOS = goodsInvoicesService.getAll(request);
        List<GoodsInvoicesDTO> filteredGoodsInvoicesDTOS = new ArrayList<>();

        for (GoodsInvoicesDTO goodsInvoicesDTO : goodsInvoicesDTOS) {
            if (goodsInvoicesDTO.getGoods().getSubcategoriesGoodsName().equals(subcategory)){
                filteredGoodsInvoicesDTOS.add(goodsInvoicesDTO);
            }
        }
        return new ResponseEntity<>(filteredGoodsInvoicesDTOS, HttpStatus.OK);
    }

    @GetMapping("/getFromFilter")
    public ResponseEntity<Iterable<GoodsInvoicesDTO>> getFromFilter(
            HttpServletRequest request,
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "subcategory", required = false) String subcategory)
    {
        System.out.println("subcategory: " + subcategory);
        System.out.println("category: " + category);



        ResponseEntity<Iterable<GoodsInvoicesDTO>> filteredGoodsInvoicesDTOS = null;
        if (subcategory != null) {
            System.out.println("subcategory if: " + subcategory);
            filteredGoodsInvoicesDTOS = getForSubcategory(request,subcategory);
        } else if (category != null) {
            System.out.println("category  if: " + category);
            filteredGoodsInvoicesDTOS = getForCategory(request,category);
        } else {
            System.out.println("getAll if: getAll" );
            return new ResponseEntity<>(goodsInvoicesService.getAll(request), HttpStatus.OK);
        }





        return filteredGoodsInvoicesDTOS;
    }


    @GetMapping("/getOne")
    public ResponseEntity<GoodsInvoicesDTO> getOneGoodsInvoices(@RequestParam Long id, HttpServletRequest request) {
//        System.out.println("id: " + id);
        try {
            GoodsInvoicesDTO dto = goodsInvoicesService.getOne(id);

            String currentUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            List<PhotosGoodsDTO> photos = dto.getGoods().getPhotosGoodsDTOS();
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
            dto.getGoods().setPropertiesGoodsDTOS(propertiesDTOList);

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GoodsInvoicesDTO()); // or handle the error
        }
    }
    @GetMapping("/getByCategoriesId")
    public ResponseEntity<Iterable<GoodsInvoicesDTO>> getByCategoriesId(
            HttpServletRequest request,
            @RequestParam(name = "id", required = false) Long id
    ) {
        List<GoodsInvoicesDTO> goodsInvoicesDTOS = goodsInvoicesService.getByCategoriesId(request, id);
        return new ResponseEntity<>(goodsInvoicesDTOS, HttpStatus.OK);
    }

    @GetMapping("/getBySubcategoriesId")
    public ResponseEntity<Iterable<GoodsInvoicesDTO>> getBySubcategoriesId(
            HttpServletRequest request,
            @RequestParam(name = "id", required = false) Long id
    ) {
        List<GoodsInvoicesDTO> goodsInvoicesDTOS = goodsInvoicesService.getBySubcategoriesId(request, id);
        return new ResponseEntity<>(goodsInvoicesDTOS, HttpStatus.OK);
    }

    @GetMapping("/getByGoodsId")
    public ResponseEntity<Iterable<GoodsInvoicesDTO>> getByGoodsId(
            HttpServletRequest request,
            @RequestParam(name = "id", required = false) Long id
    ) {
        List<GoodsInvoicesDTO> goodsInvoicesDTOS = goodsInvoicesService.getByGoodsId(request, id);
        return new ResponseEntity<>(goodsInvoicesDTOS, HttpStatus.OK);
    }
}
