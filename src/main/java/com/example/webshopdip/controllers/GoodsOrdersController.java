package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.*;
import com.example.webshopdip.entities.*;
import com.example.webshopdip.repositories.*;
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
    private GoodsInvoicesService goodsInvoicesService;
    @Autowired
    private OrderListsController orderListsController;
    @Autowired
    private OrdersListsRepository ordersListsRepository;
    @Autowired
    private GoodsOrdersRepository goodsOrdersRepository;
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
    @PostMapping
    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(@RequestBody GoodsOrdersToSaveDTO goodsOrdersToSaveDTO) {

        try {
            Optional<GoodsInvoicesEntity> goodsInvoicesEntityOptional = goodsInvoicesRepository.findById(goodsOrdersToSaveDTO.getGoodsInvoicesId());
//            System.out.println("goodsInvoicesEntityOptional: " + goodsInvoicesEntityOptional);
//            System.out.println("goodsOrdersDTO: " + goodsOrdersDTO);
            if (goodsInvoicesEntityOptional.isPresent()) {
                GoodsInvoicesEntity goodsInvoicesEntity = goodsInvoicesEntityOptional.get();
                goodsOrdersToSaveDTO.setGoodsInvoicesId(goodsInvoicesService.entityToDTO(goodsInvoicesEntity).getId());
                GoodsOrdersDTO createOrders = goodsOrdersService.createGoodsOrders(goodsOrdersToSaveDTO);

                return ResponseEntity.ok(createOrders);
            } else {
                return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити випадок, коли товар не знайдено
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // Обробити помилку
        }
    }
    @PostMapping("/addToCart")
    public ResponseEntity<GoodsOrdersDTO> addToCart(@RequestBody GoodsOrdersToSaveDTO goodsOrdersToSaveDTO){
//чи є в OrdersLists запис де поле Number пусте, а поле customerId==id покупця
//        якщо є такий запис то ordersListsId(Postman)= id цього запису
// якщо немає то створюємо запис

        return createGoodsOrders(goodsOrdersToSaveDTO);
    }
//    @PostMapping("/createGoodsOrders")
//    public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(
//            @RequestParam Long customersId,
//            @RequestParam Long goodsInvoicesId) {
//        try {
//            GoodsOrdersDTO createdGoodsOrder = goodsOrdersService.createGoodsOrders(customersId, goodsInvoicesId);
//            return ResponseEntity.ok(createdGoodsOrder);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new GoodsOrdersDTO());
//        }
//    }


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
//            GoodsOrdersDTO createdGoodsOrder = goodsOrdersService.createGoodsOrders(goodsOrdersDTO);
//            return ResponseEntity.ok(createdGoodsOrder);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new GoodsOrdersDTO());
//        }
//    }

    @GetMapping//виправлено 18.10.23 18:10
    public ResponseEntity<List<GoodsOrdersDTO>> getAll(HttpServletRequest request) {
        List<GoodsOrdersDTO> goodsOrdersEntities = goodsOrdersService.getAll(request);
//        Iterable<GoodsOrdersEntity> goodsOrdersEntities = goodsOrdersRepository.findAll();
        return new ResponseEntity<>(goodsOrdersEntities, HttpStatus.OK);
    }

//    @GetMapping("/getAllByCustomer")//18.10 після 21 00
//    public ResponseEntity<Iterable<GoodsOrdersEntity>> getAllByCustomer(@RequestParam Long id) {
//        return new ResponseEntity<>(goodsOrdersService.getAllByCustomer(id), HttpStatus.OK);
//    }
@GetMapping("/getAllByCustomer")//22.10 23:30
public ResponseEntity<Iterable<GoodsOrdersDTO>> getAllByCustomer(@RequestParam Long id) {
    Iterable<GoodsOrdersEntity> goodsOrdersByCustomer = goodsOrdersService.getAllByCustomer(id);
    Iterable<GoodsOrdersDTO> goodsOrdersDTOByCustomer = goodsOrdersService.convertToDTO(goodsOrdersByCustomer);
    return ResponseEntity.ok(goodsOrdersDTOByCustomer);
}


    @GetMapping("/getOne")//18.10 після 21 00
    public ResponseEntity<GoodsOrdersDTO> getOneGoodsOrders(@RequestParam Long id, HttpServletRequest request) {
//        System.out.println("id: " + id);
        try {
            GoodsOrdersDTO dto = goodsOrdersService.getOne(id);
//
////            GoodsInvoicesDTO goodsInvoicesDTO = goodsInvoicesService.getOne(dto.getGoodsInvoicesDTO().getId());
////            goodsInvoicesDTO
//            String currentUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
//
//            List<PhotosGoodsDTO> photos = dto.getGoodsInvoicesDTO().getGoods().getPhotosGoodsDTOS();
//            for (PhotosGoodsDTO photo : photos) {
//                String imagePath = currentUrl + "/images/" + photo.getPath();
//                photo.setPath(imagePath);
//            }
//
//            GoodsInvoicesEntity entity = goodsInvoicesRepository.findById(id).orElse(new GoodsInvoicesEntity());
//
//            System.out.println("PropertiesGoods.size: " + entity.getGoods().getPropertiesGoods().size());
//            List<PropertiesGoodsEntity> propertiesGoodsList = entity.getGoods().getPropertiesGoods();
//            List<PropertiesGoodsDTO> propertiesDTOList = new ArrayList<>();
//            for (PropertiesGoodsEntity propertiesGoods : propertiesGoodsList) {
//                PropertiesNameGoodsEntity propertiesNameGoods = propertiesGoods.getPropertiesNameGoods();
//                PropertiesGoodsDTO propertiesGoodsDTO = new PropertiesGoodsDTO();
//                propertiesGoodsDTO.setId(propertiesGoods.getId());
//                propertiesGoodsDTO.setValue(propertiesGoods.getValue());
//                propertiesGoodsDTO.setPropertiesName(propertiesNameGoods.getName()); // Додаємо назву властивості
//                propertiesGoodsDTO.setType(propertiesNameGoods.getValueType()); // Додаємо тип значення властивості
//                propertiesDTOList.add(propertiesGoodsDTO);
//            }
//            dto.getGoodsInvoicesDTO().getGoods().setPropertiesGoodsDTOS(propertiesDTOList);

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GoodsOrdersDTO()); // or handle the error
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGoodsOrders(@PathVariable Long id) {
        try {

            goodsOrdersService.delete(id);

            return ResponseEntity.ok("Delete order");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
