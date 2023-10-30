package com.example.webshopdip.services;

import com.example.webshopdip.controllers.GoodsInvoicesController;
import com.example.webshopdip.controllers.OrderListsController;
import com.example.webshopdip.dtos.*;
import com.example.webshopdip.entities.*;
import com.example.webshopdip.exceptions.GoodsOrdersNotFoundException;
import com.example.webshopdip.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
    private OrderListsController orderListsController;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private OrdersListsRepository ordersListsRepository;
    @Autowired
    private OrdersListsService ordersListsService;

    /**
     * public ResponseEntity<GoodsOrdersToSaveDTO> createGoodsOrders(@RequestBody Map<String, Object> request) {
     * Long customers_Id = (Long) request.get("customers_Id");
     * Long goodsInvoices_Id = (Long) request.get("goodsInvoices_Id");
     * Integer quantity = (Integer) request.get("quantity");
     * Float price = (Float) request.get("price");
     * goodsInvoicesRepository.findById(GoodsOrdersToSaveDTO.getGoodsInvoicesDTO().getId()).orElse(null)
     */
    //    public GoodsOrdersToSaveDTO createGoodsOrders(GoodsOrdersToSaveDTO dto) {
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
    //public GoodsOrdersToSaveDTO createGoodsOrder(Long customersId, Long goodsInvoicesId, Integer quantity, Float price) {
    // Тут додайте логіку створення запису GoodsOrdersEntity
    // перевірка на існування відповідного замовлення і товару, створення нового замовлення якщо не існує
    // і додавання запису GoodsOrdersEntity
    // Після цього перетворіть отриманий об'єкт в DTO і поверніть його.
//}
    //public GoodsOrdersToSaveDTO createGoodsOrders(Long customersId, Long goodsInvoicesId) throws GoodsOrdersNotFoundException {
    public GoodsOrdersDTO createGoodsOrders(GoodsOrdersToSaveDTO goodsOrdersToSaveDTO) throws GoodsOrdersNotFoundException {
 /*
          {
    "goodsInvoicesId":4,   "ordersListsId":4,  "price": 100.0,   "quantity": 2
}
         */
        GoodsOrdersEntity goodsOrdersEntity = new GoodsOrdersEntity();
//        Optional<GoodsOrdersToSaveDTO> optional1 = goodsOrdersRepository.findById(goodsOrdersToSaveDTO.getId());

        goodsOrdersEntity.setGoodsInvoices(goodsInvoicesRepository.findById(goodsOrdersToSaveDTO.getGoodsInvoicesId()).orElse(null));
        goodsOrdersEntity.setOrdersLists(ordersListsRepository.findById(goodsOrdersToSaveDTO.getOrdersListsId()).orElse(null));
        goodsOrdersEntity.setPrice(goodsOrdersToSaveDTO.getPrice());
        goodsOrdersEntity.setQuantity(goodsOrdersToSaveDTO.getQuantity());
        //перевіряємо чи є в ордер ліст запис без номера щодо данного покупця.
        //якщо є то додаємо до того ід запису наш перелік товарів в кошик
        //якщо немає то створюємо цей запис і додаємо перелік товарів
        goodsOrdersRepository.save(goodsOrdersEntity);
        return entityToDTO(goodsOrdersEntity);


    }


    public GoodsOrdersDTO entityToDTO(GoodsOrdersEntity entity) {//21.10 00:40
        GoodsOrdersDTO dto = new GoodsOrdersDTO();
        dto.setId(entity.getId());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());

        //dto.setGoodsInvoicesDTO(goodsInvoicesService.entityToDTO(entity.getGoodsInvoices()));//22.10 22:50
        //dto.setOrdersListsDTO(entity.getOrdersLists());

        GoodsInvoicesEntity goodsInvoicesEntity = entity.getGoodsInvoices();
        GoodsInvoicesDTO goodsInvoicesDTO = goodsInvoicesService.entityToDTO(goodsInvoicesEntity);
        dto.setGoodsInvoicesDTO(goodsInvoicesDTO);

        OrdersListsEntity ordersListsEntity = entity.getOrdersLists();
        OrdersListsDTO ordersListsDTO = ordersListsService.entityToDTO(ordersListsEntity);
        dto.setOrdersListsDTO(ordersListsDTO);

        return dto;
    }


    public List<GoodsOrdersDTO> getAll(HttpServletRequest request) {
        List<GoodsOrdersEntity> goodsOrdersEntities = goodsOrdersRepository.findAll();

        return getListEntitiesToDTOS(request, goodsOrdersEntities);
    }

    public GoodsOrdersDTO getOne(Long id) {
        GoodsOrdersEntity goodsOrdersEntity = goodsOrdersRepository.findById(id).orElse(null);
        GoodsInvoicesDTO goodsInvoicesDTO = goodsInvoicesService.getOne(goodsOrdersEntity.getGoodsInvoices().getId());
        GoodsOrdersDTO GoodsOrdersDTO = entityToDTO(goodsOrdersEntity);
        GoodsOrdersDTO.setGoodsInvoicesDTO(goodsInvoicesDTO);
        return GoodsOrdersDTO;
    }

    public List<GoodsOrdersDTO> getListEntitiesToDTOS(HttpServletRequest request, Iterable<GoodsOrdersEntity> goodsOrdersEntities) {
        List<GoodsOrdersDTO> GoodsOrdersDTOS = new ArrayList<>();


        for (GoodsOrdersEntity goodsOrdersEntity : goodsOrdersEntities) {
            GoodsOrdersDTO dto = entityToDTO(goodsOrdersEntity);
            GoodsOrdersDTOS.add(dto);
        }

        String currentUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        for (GoodsOrdersDTO goodsOrdersDTO : GoodsOrdersDTOS) {
            GoodsInvoicesDTO goodsInvoicesDTO = goodsOrdersDTO.getGoodsInvoicesDTO();
            if (goodsInvoicesDTO != null) {
                List<PhotosGoodsDTO> photosDTOList = goodsInvoicesDTO.getGoods().getPhotosGoodsDTOS();

                for (PhotosGoodsDTO photoDTO : photosDTOList) {
                    String imagePath = currentUrl + "/images/" + photoDTO.getPath();
                    photoDTO.setPath(imagePath);
                }
            }
        }
        return GoodsOrdersDTOS;
    }

    @Transactional
    public void delete(Long id) {//перевірено 21.10 15:30 - працює
        goodsOrdersRepository.deleteById(id);
    }

    //ВІДОБРАЖЕННЯ ТОВАРІВ КОНКРЕТНОГО customer
//    public  Iterable<GoodsOrdersEntity> getAllByCustomer(Long customerId) {
//        Iterable<GoodsOrdersEntity> goodsOrdersEntities = goodsOrdersRepository.findAll();
//        List<GoodsOrdersEntity> goodsOrdersByCustomer=new ArrayList<>();
//        for (GoodsOrdersEntity goodsOrdersEntity:goodsOrdersEntities){
//            if (goodsOrdersEntity.getOrdersLists().getCustomers().getId().equals(customerId)) {
//                goodsOrdersByCustomer.add(goodsOrdersEntity);
//            }
//
//        }
//        return goodsOrdersByCustomer;
//    }
    //22.10 23:30
    public Iterable<GoodsOrdersDTO> convertToDTO(Iterable<GoodsOrdersEntity> goodsOrdersEntities) {
        List<GoodsOrdersDTO> GoodsOrdersDTOs = new ArrayList<>();

        for (GoodsOrdersEntity entity : goodsOrdersEntities) {
            GoodsOrdersDTO dto = entityToDTO(entity);
            GoodsOrdersDTOs.add(dto);
        }

        return GoodsOrdersDTOs;
    }

    //22.10 23:30
    public Iterable<GoodsOrdersEntity> getAllByCustomer(Long customerId) {
        Iterable<GoodsOrdersEntity> goodsOrdersEntities = goodsOrdersRepository.findAll();
        List<GoodsOrdersEntity> goodsOrdersByCustomer = new ArrayList<>();

        for (GoodsOrdersEntity goodsOrdersEntity : goodsOrdersEntities) {
            if (goodsOrdersEntity.getOrdersLists().getCustomers().getId().equals(customerId)) {
                goodsOrdersByCustomer.add(goodsOrdersEntity);
            }
        }

        return goodsOrdersByCustomer;
    }


}
