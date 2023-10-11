package com.example.webshopdip.controllers;


import com.example.webshopdip.dtos.CategoriesGoodsDTO;
import com.example.webshopdip.dtos.OrdersListsDTO;
import com.example.webshopdip.entities.CategoriesGoodsEntity;
import com.example.webshopdip.entities.OrdersListsEntity;
import com.example.webshopdip.exceptions.CategoriesGoodsNotFoundException;
import com.example.webshopdip.exceptions.OrdersListsNotFoundException;
import com.example.webshopdip.repositories.OrdersListsRepository;
import com.example.webshopdip.services.OrdersListsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderLists")
public class OrderListsController {
    @Autowired
    private OrdersListsService ordersListsService ;
    @Autowired
    private OrdersListsRepository ordersListsRepository;

    @PostMapping
    public ResponseEntity<OrdersListsDTO> createOrdersLists(@RequestBody OrdersListsDTO dto) {
        try {
            OrdersListsDTO createdDTO = ordersListsService.createOrdersLists(dto);
            return ResponseEntity.ok(createdDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new OrdersListsDTO()); // or handle the error
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
