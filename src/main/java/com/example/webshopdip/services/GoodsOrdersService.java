package com.example.webshopdip.services;

import com.example.webshopdip.dtos.*;
import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.entities.GoodsInvoicesEntity;
import com.example.webshopdip.entities.GoodsOrdersEntity;
import com.example.webshopdip.repositories.GoodsInvoicesRepository;
import com.example.webshopdip.repositories.GoodsOrdersRepository;
import com.example.webshopdip.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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

    public GoodsOrdersDTO createGoodsOrders(GoodsOrdersDTO dto) {
        GoodsOrdersEntity entity = new GoodsOrdersEntity();

        // Отримати GoodsEntity з Optional<GoodsEntity> і перевірити, чи він присутній
        Optional<GoodsEntity> goodsEntityOptional = goodsRepository.findById(dto.getGoodsInvoicesDTO().getGoods().getId());
        //перевіряємо чи є в ордер ліст запис без номера щодо данного покупця.
        //якщо є то додаємо до того ід запису наш перелік товарів в кошик
        //якщо немає то створюємо цей запис і додаємо перелік товарів
        if (goodsEntityOptional.isPresent()) {
            GoodsEntity goodsEntity = goodsEntityOptional.get(); // Отримати GoodsEntity з Optional

            GoodsInvoicesEntity entityInvoices = new GoodsInvoicesEntity();
            entityInvoices.setGoods(goodsEntity);
            entityInvoices.setQuantity(dto.getQuantity());
            entityInvoices.setPrice(dto.getPrice());

            entity.setGoodsInvoices(entityInvoices);

            return entityToDTO(entity);
        } else {
            return null;
        }
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
