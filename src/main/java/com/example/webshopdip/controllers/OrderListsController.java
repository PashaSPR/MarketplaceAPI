package com.example.webshopdip.controllers;


import com.example.webshopdip.dtos.CategoriesGoodsDTO;
import com.example.webshopdip.dtos.GoodsOrdersDTO;
import com.example.webshopdip.dtos.OrdersListsDTO;
import com.example.webshopdip.entities.CategoriesGoodsEntity;
import com.example.webshopdip.entities.OrdersListsEntity;
import com.example.webshopdip.exceptions.CategoriesGoodsNotFoundException;
import com.example.webshopdip.exceptions.OrdersListsNotFoundException;
import com.example.webshopdip.repositories.OrdersListsRepository;
import com.example.webshopdip.services.GoodsOrdersService;
import com.example.webshopdip.services.OrdersListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orderLists")
public class OrderListsController {
    @Autowired
    private OrdersListsService ordersListsService ;
    @Autowired
    private OrdersListsRepository ordersListsRepository;

//    public static GoodsOrdersDTO createGoodsOrder(GoodsOrdersDTO goodsOrders) {
//        //goodsOrdersService.entityToDTO(goodsOrders);//якщо замовлення існує
//        return goodsOrders;
//    }


    //    @PostMapping
    //public ResponseEntity<OrdersListsDTO> createOrdersLists(@RequestBody Map<String, Long> request) {
//        public ResponseEntity<OrdersListsDTO> createOrdersLists(Long customerId) {
////        Long customerId = request.get("customers");
//        try {
//            OrdersListsDTO createdDTO = ordersListsService.addGoodsToOrder(customerId);
//            return ResponseEntity.ok(createdDTO);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new OrdersListsDTO()); // or handle the error
//        }
//    }
//@PostMapping("/createOrderList")
//public ResponseEntity<OrdersListsDTO> createOrdersLists(@RequestBody OrdersListsDTO dto) {
//    try {
//        OrdersListsDTO createdDTO = ordersListsService.createOrdersLists(dto);
//        return ResponseEntity.ok(createdDTO);
//    } catch (Exception e) {
//        return ResponseEntity.badRequest().body(new OrdersListsDTO());
//    }
//}

    @GetMapping("/getOrderListId")
    public ResponseEntity<Long> getOrderListId(@RequestParam Long customerId) {
        try {
            OrdersListsDTO ordersLists = ordersListsService.getOrdersListByCustomer(customerId);
            return ResponseEntity.ok(ordersLists.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(-1L); // або інший ідентифікатор помилки
        }
    }
    @GetMapping("/getOne")
    public ResponseEntity getOneOrdersLists(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(ordersListsService.getOne(id));
        }
//        catch (OrdersListsNotFoundException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Виникла помилка");
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<OrdersListsEntity>> getAll() {
        Iterable<OrdersListsEntity> ordersListsEntities = ordersListsRepository.findAll();
        return new ResponseEntity<>(ordersListsEntities, HttpStatus.OK);
    }

}
