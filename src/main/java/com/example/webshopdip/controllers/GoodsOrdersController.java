package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.*;
import com.example.webshopdip.entities.*;
import com.example.webshopdip.repositories.CustomersRepository;
import com.example.webshopdip.repositories.GoodsInvoicesRepository;
import com.example.webshopdip.repositories.GoodsRepository;
import com.example.webshopdip.repositories.OrdersListsRepository;
import com.example.webshopdip.services.GoodsInvoicesService;
import com.example.webshopdip.services.GoodsOrdersService;
import com.example.webshopdip.services.GoodsService;
import com.example.webshopdip.services.OrdersListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.persistence.Convert;
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
    private OrdersListsService ordersListsService;
    @Autowired
    private OrderListsController orderListsController;
    @Autowired
    private OrdersListsRepository ordersListsRepository;
    private GoodsInvoicesService goodsInvoicesService;
    @Autowired
    private GoodsInvoicesRepository goodsInvoicesRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private CustomersRepository customersRepository;
    /*
    id customers через ордер ліст з перевіркою
    id goodsInvoices
    quantity з goodsInvoices(не більше ніж залишок в goodsInvoices)
    price ставить автоматом з goodsInvoices (продавець)
    createGoodsOrders(id goodsInvoices,){
   перевіряє чи є запис з конкретним id customers та порожнім номером(number) замовлення,тобто порожнім записом,
   якщо є то id цього запису додаємо orderListId
   якщо немає то створюємо createOrdersList та беремо id новоствореного запису і додаємо запис orderListId
    orderListsController.createOrdersLists(1);
    }
    * */
//    @PostMapping
//    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(@RequestBody GoodsOrdersDTO goodsOrdersDTO) {
//
//        try {
//            Optional<GoodsEntity> goodsEntityOptional = goodsRepository.findById(goodsOrdersDTO.getGoodsInvoicesDTO().getGoods().getId());
//
//            if (goodsEntityOptional.isPresent()) {
//                GoodsEntity goodsEntity = goodsEntityOptional.get();
//
//                GoodsInvoicesDTO goodsInvoicesDTO = goodsInvoicesService.getOne(goodsOrdersDTO.getGoodsInvoicesDTO().getId());
//                goodsInvoicesDTO.setGoods(goodsService.entityToDTO(goodsEntity));
//
//                goodsOrdersDTO.setGoodsInvoicesDTO(goodsInvoicesDTO);
//
////                GoodsOrdersDTO createdDTO = goodsOrdersService.createGoodsOrders(goodsOrdersDTO);
////                return ResponseEntity.ok(createdDTO);
//                orderListsController.createOrdersLists(goodsEntity.getId());
//                return ResponseEntity.ok(goodsOrdersDTO);
//            } else {
//                return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити випадок, коли товар не знайдено
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити помилку
//        }
//    }
    @PostMapping("/createGoodsOrders")
    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(
            @RequestParam Long customersId,
            @RequestParam Long goodsInvoicesId) {
        try {
            GoodsOrdersDTO createdGoodsOrder = goodsOrdersService.createGoodsOrders(customersId, goodsInvoicesId);
            return ResponseEntity.ok(createdGoodsOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GoodsOrdersDTO());
        }
    }


//    @PostMapping("/createGoodsOrders")
//    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(
//            @RequestParam Long customers_Id,
//            @RequestParam Long goodsInvoices_Id,
//            @RequestParam Integer quantity,
//            @RequestParam Float price) {
//        //ПЕРЕДЕЛЕГУВАТИ ЦЕ СЕРВІСУ
//        /**
//         public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(@RequestBody Map<String, Object> request) {
//         Long customers_Id = (Long) request.get("customers_Id");
//         Long goodsInvoices_Id = (Long) request.get("goodsInvoices_Id");
//         Integer quantity = (Integer) request.get("quantity");
//         Float price = (Float) request.get("price");
//         */
//        // Перевірте, чи існує запис з порожнім номером (number) та вказаним id клієнта
//        Long customerId = customers_Id;
//        Long orderListId;
//        ResponseEntity<Long> response = orderListsController.getOrderListId(customerId);
//        if (response.getStatusCode() == HttpStatus.OK) {
//            orderListId = response.getBody();
//        } else {
//            // Якщо такого запису немає, створіть новий запис замовлення
//            OrdersListsDTO newOrderList = new OrdersListsDTO();
//            CustomersEntity customers = customersRepository.findById(customers_Id).orElse(null);
//            newOrderList.setCustomers(customers);
//            ResponseEntity<OrdersListsDTO> orderListResponse = orderListsController.createOrdersLists(newOrderList);
//            if (orderListResponse.getStatusCode() == HttpStatus.OK) {
//                orderListId = orderListResponse.getBody().getId();
//            } else {
//                // Обробити помилку створення запису замовлення
//                return ResponseEntity.badRequest().body(new GoodsOrdersDTO());
//            }
//        }
//
//        // Тепер ви маєте `orderListId` і можете створити запис `GoodsOrdersEntity`
//        GoodsOrdersEntity goodsOrders = new GoodsOrdersEntity();
//        OrdersListsEntity ordersListsEntity=ordersListsRepository.findById(orderListId).orElse(null);
//        goodsOrders.setOrdersLists(ordersListsEntity);
//        GoodsInvoicesEntity goodsInvoicesEntity=goodsInvoicesRepository.findById(goodsInvoices_Id).orElse(null);
//        goodsOrders.setGoodsInvoices(goodsInvoicesEntity);
//        goodsOrders.setQuantity(quantity);
//        goodsOrders.setPrice(price);
//
//        // Додайте запис `GoodsOrdersEntity` та поверніть його
//        GoodsOrdersDTO createdGoodsOrder = OrderListsController.createGoodsOrder(goodsOrdersService.entityToDTO(goodsOrders));
//        return ResponseEntity.ok(createdGoodsOrder);
//    }


//    @PostMapping()
//    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(@RequestBody GoodsOrdersDTO goodsOrdersDTO) {
//        try {
//
//            Optional<GoodsInvoicesEntity> goodsInvoicesEntityOptional = goodsInvoicesRepository.findById(goodsOrdersDTO.getGoodsInvoicesDTO().getGoods().getId());
//            Long customer_id = customersRepository.findById()
//            if (goodsInvoicesEntityOptional.isPresent()) {
//                GoodsInvoicesEntity goodsEntity = goodsInvoicesEntityOptional.get();
//
//                GoodsInvoicesDTO goodsInvoicesDTO = goodsInvoicesService.getOne(goodsOrdersDTO.getGoodsInvoicesDTO().getId());
//                goodsOrdersDTO.setGoodsInvoicesDTO(goodsInvoicesDTO);
//
//                GoodsOrdersDTO createdDTO = goodsOrdersService.createGoodsOrders(goodsOrdersDTO);
//
//                // Викликаємо метод для створення списку замовлень
//                orderListsController.createOrdersLists(goodsEntity.getId());
//
//                return ResponseEntity.ok(createdDTO);
//            } else {
//                return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити випадок, коли товар не знайдено
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити помилку
//        }
//    }

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
