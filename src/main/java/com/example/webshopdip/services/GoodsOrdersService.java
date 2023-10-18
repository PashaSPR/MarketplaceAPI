package com.example.webshopdip.services;

import com.example.webshopdip.controllers.OrderListsController;
import com.example.webshopdip.dtos.*;
import com.example.webshopdip.entities.*;
import com.example.webshopdip.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GoodsOrdersService {
    @Autowired
    GoodsOrdersRepository goodsOrdersRepository;
    @Autowired
    GoodsInvoicesRepository goodsInvoicesRepository;
    @Autowired
    GoodsInvoicesService goodsInvoicesService;
    @Autowired
    private SubcategoriesGoodsService subcategoriesGoodsService;
    @Autowired
    private PhotosGoodsService photosGoodsService;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private OrderListsController orderListsController;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private OrdersListsRepository ordersListsRepository;
    @Autowired
    private OrdersListsService ordersListsService ;

//    public GoodsOrdersDTO createGoodsOrders(GoodsOrdersDTO dto) {
//        GoodsOrdersEntity entity = new GoodsOrdersEntity();
//
//        // Отримати GoodsEntity з Optional<GoodsEntity> і перевірити, чи він присутній
//        Optional<GoodsEntity> goodsEntityOptional = goodsRepository.findById(dto.getGoodsInvoicesDTO().getGoods().getId());
//        //перевіряємо чи є в ордер ліст запис без номера щодо данного покупця.
//        //якщо є то додаємо до того ід запису наш перелік товарів в кошик
//        //якщо немає то створюємо цей запис і додаємо перелік товарів
//        if (goodsEntityOptional.isPresent()) {
//            GoodsEntity goodsEntity = goodsEntityOptional.get(); // Отримати GoodsEntity з Optional
//
//            GoodsInvoicesEntity entityInvoices = new GoodsInvoicesEntity();
//            entityInvoices.setGoods(goodsEntity);
//            entityInvoices.setQuantity(dto.getQuantity());
//            entityInvoices.setPrice(dto.getPrice());
//
//            entity.setGoodsInvoices(entityInvoices);
//
//            return entityToDTO(entity);
//        } else {
//            return null;
//        }
    //   }
    //public GoodsOrdersDTO createGoodsOrder(Long customersId, Long goodsInvoicesId, Integer quantity, Float price) {
    // Тут додайте логіку створення запису GoodsOrdersEntity
    // перевірка на існування відповідного замовлення і товару, створення нового замовлення якщо не існує
    // і додавання запису GoodsOrdersEntity
    // Після цього перетворіть отриманий об'єкт в DTO і поверніть його.
//}
    public GoodsOrdersDTO createGoodsOrders(Long customersId, Long goodsInvoicesId) {

        /**
         public ResponseEntity<GoodsOrdersDTO> createGoodsOrders(@RequestBody Map<String, Object> request) {
         Long customers_Id = (Long) request.get("customers_Id");
         Long goodsInvoices_Id = (Long) request.get("goodsInvoices_Id");
         Integer quantity = (Integer) request.get("quantity");
         Float price = (Float) request.get("price");
         */
        // Перевірте, чи існує запис з порожнім номером (number) та вказаним id клієнта
        Long customer_Id = customersId;
        Long orderListId;
        ResponseEntity<Long> response = orderListsController.getOrderListId(customer_Id);
        if (response.getStatusCode() == HttpStatus.OK) {
            orderListId = response.getBody();
        } else {
            // Якщо такого запису немає, створіть новий запис замовлення
            OrdersListsDTO newOrderList = new OrdersListsDTO();
            CustomersEntity customers = customersRepository.findById(customer_Id).orElse(null);
            newOrderList.setCustomers(customers);
            ResponseEntity<OrdersListsDTO> orderListResponse = ordersListsService.createOrdersLists(customer_Id);
            if (orderListResponse.getStatusCode() == HttpStatus.OK) {
                orderListId = orderListResponse.getBody().getId();
                //orderListId = Objects.requireNonNull(orderListResponse.getBody()).getId();
            } else {
                // Обробити помилку створення запису замовлення
                return new GoodsOrdersDTO();
            }
        }

        // Тепер ви маєте `orderListId` і можете створити запис `GoodsOrdersEntity`
        GoodsOrdersEntity goodsOrders = new GoodsOrdersEntity();
        OrdersListsEntity ordersListsEntity=ordersListsRepository.findById(orderListId).orElse(null);

        goodsOrders.setOrdersLists(ordersListsEntity);
        GoodsInvoicesEntity goodsInvoicesEntity=goodsInvoicesRepository.findById(goodsInvoicesId).orElse(null);
        goodsOrders.setGoodsInvoices(goodsInvoicesEntity);
        goodsOrders.setQuantity(goodsInvoicesEntity.getQuantity());
        goodsOrders.setPrice(goodsInvoicesEntity.getPrice());

        // Додайте запис `GoodsOrdersEntity` та поверніть його
       // GoodsOrdersDTO createdGoodsOrder = OrderListsController.createGoodsOrder(entityToDTO(goodsOrders));

            return entityToDTO(goodsOrders);
    }

    public GoodsOrdersDTO entityToDTO(GoodsOrdersEntity entity) {
        GoodsOrdersDTO dto = new GoodsOrdersDTO();
        dto.setId(entity.getId());

        GoodsInvoicesDTO goodsInvoicesDTO = new GoodsInvoicesDTO();
        goodsInvoicesDTO.setId(entity.getGoodsInvoices().getId());

        // Перетворюємо GoodsEntity на GoodsGetAllDTO
        GoodsGetAllDTO goodsGetAllDTO = new GoodsGetAllDTO();
        goodsGetAllDTO.setId(entity.getGoodsInvoices().getGoods().getId());
        // ??? Встановлюємо інші властивості GoodsDTO, які вам потрібні
        goodsGetAllDTO.setName(entity.getGoodsInvoices().getGoods().getName());
        goodsGetAllDTO.setShort_discription(entity.getGoodsInvoices().getGoods().getShort_discription());
        goodsGetAllDTO.setSubcategoriesGoodsName(subcategoriesGoodsService.entityToDTO(entity.getGoodsInvoices().getGoods().getSubcategoriesGoods()).getName());
        goodsGetAllDTO.setPhotosGoodsDTOS(photosGoodsService.entityListToDTOS(entity.getGoodsInvoices().getGoods().getPhotosGoods()));

        goodsInvoicesDTO.setGoods(goodsGetAllDTO);

        dto.setGoodsInvoicesDTO(goodsInvoicesDTO);
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());

        return dto;
    }


    public List<GoodsOrdersDTO> getAll(HttpServletRequest request) {
        Iterable<GoodsOrdersEntity> goodsOrdersEntities = goodsOrdersRepository.findAll();

        return getListEntitiesToDTOS(request, goodsOrdersEntities);
    }

    public GoodsOrdersDTO getOne(Long id) {
        Optional<GoodsOrdersEntity> optional = goodsOrdersRepository.findById(id);

        GoodsOrdersEntity entity = optional.get();
        return entityToDTO(entity);
    }
    public List<GoodsOrdersDTO> getListEntitiesToDTOS(HttpServletRequest request, Iterable<GoodsOrdersEntity> goodsOrdersEntities) {
        List<GoodsOrdersDTO> goodsOrdersDTOS = new ArrayList<>();


        for (GoodsOrdersEntity goodsOrdersEntity : goodsOrdersEntities) {
            GoodsOrdersDTO dto = entityToDTO(goodsOrdersEntity);
            goodsOrdersDTOS.add(dto);
        }

        String currentUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        for (GoodsOrdersDTO dto : goodsOrdersDTOS) {
            List<PhotosGoodsDTO> photosDTOList = dto.getGoodsInvoicesDTO().getGoods().getPhotosGoodsDTOS();

            for (PhotosGoodsDTO photoDTO : photosDTOList) {
                String imagePath = currentUrl + "/images/" + photoDTO.getPath();
                photoDTO.setPath(imagePath);
//                System.out.println("Image path: " + imagePath);
            }
        }
        return goodsOrdersDTOS;
    }
    @Transactional
    public void delete(Long id) {
        goodsOrdersRepository.deleteById(id);
    }


}
