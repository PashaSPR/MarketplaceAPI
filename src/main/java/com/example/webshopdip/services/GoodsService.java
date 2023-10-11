package com.example.webshopdip.services;

import com.example.webshopdip.dtos.GoodsDTO;
import com.example.webshopdip.dtos.GoodsForCatalogDTO;
import com.example.webshopdip.dtos.GoodsGetAllDTO;
import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.entities.PropertiesGoodsEntity;
import com.example.webshopdip.entities.PropertiesNameGoodsEntity;
import com.example.webshopdip.entities.SubcategoriesGoodsEntity;
import com.example.webshopdip.exceptions.DuplicateAssociationException;
import com.example.webshopdip.exceptions.GoodsAlreadyExistException;
import com.example.webshopdip.exceptions.GoodsNotFoundException;
import com.example.webshopdip.exceptions.PropertiesGoodsNotFoundException;
import com.example.webshopdip.repositories.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoodsService {
//    @Autowired
//    private GoodsRepository goodsRepository;
//    public GoodsEntity goodsRegistration (GoodsEntity goodsEntity) throws GoodsAlreadyExistException {
//        if (goodsRepository.findByName(goodsEntity.getName()) != null){
//            throw new GoodsAlreadyExistException("Такий товар вже існує");
//        }
//        return goodsRepository.save(goodsEntity);
//    }

    @Autowired
    private final GoodsRepository goodsRepository;
    @Autowired
    private final SubcategoriesGoodsRepository subcategoriesGoodsRepository;
    @Autowired
    private final PhotosGoodsRepository photosGoodsRepository;
    @Autowired
    private PropertiesGoodsRepository propertiesGoodsRepository;
    @Autowired
    private PropertiesNameGoodsRepository propertiesNameGoodsRepository;
    @Autowired
    private SubcategoriesGoodsService subcategoriesGoodsService;
    @Autowired
    private PhotosGoodsService photosGoodsService;

    public GoodsService(GoodsRepository goodsRepository, SubcategoriesGoodsRepository subcategoriesGoodsRepository, PhotosGoodsRepository photosGoodsRepository) {
        this.goodsRepository = goodsRepository;
        this.subcategoriesGoodsRepository = subcategoriesGoodsRepository;
        this.photosGoodsRepository = photosGoodsRepository;
    }

    //    public GoodsEntity goodsRegistration(GoodsDTO goodsDTO, MultipartFile photoFile) throws GoodsAlreadyExistException {
    public GoodsEntity createGood(GoodsDTO goodsDTO) throws GoodsAlreadyExistException {

        // Перевірка, чи існує Підкатегорія з вказаним ідентифікатором
        SubcategoriesGoodsEntity subcategoriesGoodsEntity = subcategoriesGoodsRepository.findById(goodsDTO.getSubcategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid categoryId"));

        // Перевірка, чи існує товар з вказаним ім'ям
        if (goodsRepository.findByName(goodsDTO.getName()) != null) {
            throw new GoodsAlreadyExistException("The product already exists");
        }
        // Створення об'єкту Goods з DTO
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setName(goodsDTO.getName());
        goodsEntity.setShort_discription(goodsDTO.getShort_discription());
        goodsEntity.setSubcategoriesGoods(subcategoriesGoodsEntity);
        goodsRepository.save(goodsEntity);

        List<PropertiesNameGoodsEntity> propertiesNameGoodsEntityList = subcategoriesGoodsEntity.getPropertiesNameGoodsEntities();
//       for (PropertiesNameGoodsEntity propertiesNameGoodsEntity : propertiesNameGoodsEntityList){
//           System.out.println("propertiesNameGoodsEntity: " + propertiesNameGoodsEntity.getName());
//       }
        List<PropertiesGoodsEntity> propertiesGoodsEntityList = new ArrayList<>();
//        for (PropertiesNameGoodsEntity propertiesNameGoodsEntity : propertiesNameGoodsEntityList) {
//            PropertiesGoodsEntity propertiesGoodsEntity = new PropertiesGoodsEntity();
//            propertiesGoodsEntity.setGoods(goodsEntity);
//            propertiesGoodsEntity.setPropertiesNameGoods(propertiesNameGoodsEntity);
//            propertiesGoodsEntityList.add(propertiesGoodsEntity);
//        }
        for (PropertiesNameGoodsEntity propertiesNameGoodsEntity : propertiesNameGoodsEntityList) {
            PropertiesGoodsEntity propertiesGoodsEntity = new PropertiesGoodsEntity();
            propertiesGoodsEntity.setGoods(goodsEntity);
            propertiesGoodsEntity.setPropertiesNameGoods(propertiesNameGoodsEntity);
            propertiesGoodsEntityList.add(propertiesGoodsEntity);

            propertiesGoodsRepository.save(propertiesGoodsEntity); // Додайте цей рядок
        }
//        for (PropertiesGoodsEntity propertiesGoodsEntity : propertiesGoodsEntityList) {
//            System.out.println("propertiesGoodsEntity: " + propertiesGoodsEntity);
//            System.out.println("Goods: " + propertiesGoodsEntity.getGoods());
//            System.out.println("PropertiesNameGoods: " + propertiesGoodsEntity.getPropertiesNameGoods());
//        }
        goodsEntity.setPropertiesGoods(propertiesGoodsEntityList);

        // Збереження товару в репозиторії
        return goodsRepository.save(goodsEntity);
    }


    public GoodsGetAllDTO addPropertiesToGood(Long goodId, List<Long> propertiesIds) throws GoodsNotFoundException, PropertiesGoodsNotFoundException {
        GoodsEntity goodsEntity = goodsRepository.findById(goodId)
                .orElseThrow(()-> new GoodsNotFoundException("Product not found"));

        List<PropertiesGoodsEntity> propertiesGoodsEntityList = goodsEntity.getPropertiesGoods();

        for (Long propertyId : propertiesIds) {
            for (PropertiesGoodsEntity propertiesGoods: propertiesGoodsEntityList){
                if (propertiesGoods.getPropertiesNameGoods().getId().equals(propertyId)){
                    propertiesIds.remove(propertyId);
                }
            }
        }
        for (Long propertyId : propertiesIds) {

//            System.out.println("propertiesIds: " + propertyId);
            PropertiesGoodsEntity newPropertiesGoods = new PropertiesGoodsEntity();
            newPropertiesGoods.setGoods(goodsEntity);
            newPropertiesGoods.setPropertiesNameGoods(propertiesNameGoodsRepository.findById(propertyId).get());

//            System.out.println("propertiesGoods properties : " + newPropertiesGoods.getPropertiesNameGoods().getId());
//            System.out.println("propertiesGoods Goods : " + newPropertiesGoods.getGoods().getId());
            propertiesGoodsRepository.save(newPropertiesGoods);
        }
        return entityToDTO(goodsEntity);
    }





    //Метод приймає параметри для GoodsEntity та SubcategoriesGoodsEntity
    // і встановлюває відповідні зв'язки
    public GoodsEntity assignSubcategory(GoodsEntity goods, SubcategoriesGoodsEntity subcategory) {
        goods.setSubcategoriesGoods(subcategory);
        return goodsRepository.save(goods);
    }

    //    public List<GoodsForCatalogDTO> getByCategoriesId(Long id) {
//        Iterable<GoodsEntity> goodsIterable = goodsRepository.findBySubcategoriesGoods_CategoriesGoods_Id(id);
//        List<GoodsEntity> goodsList = new ArrayList<>();
//        goodsIterable.forEach(goodsList::add);
//
//        List<GoodsForCatalogDTO> dtoList = new ArrayList<>();
//        for (GoodsEntity entity : goodsList) {
//            dtoList.add(entityToCatalogDTO(entity));
//        }
//        return dtoList;
//    }
    public List<GoodsGetAllDTO> getBySubcategoriesId(Long id) {
        Iterable<GoodsEntity> goodsIterable = goodsRepository.findBySubcategoriesGoods_Id(id);
        List<GoodsEntity> goodsList = new ArrayList<>();
        goodsIterable.forEach(goodsList::add);

        List<GoodsGetAllDTO> dtoList = new ArrayList<>();
        for (GoodsEntity entity : goodsList) {
            dtoList.add(entityToDTO(entity));
        }
        return dtoList;
    }

    public GoodsEntity getGoodsById(Long id) throws GoodsNotFoundException {
        Optional<GoodsEntity> optional = goodsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new GoodsNotFoundException("Product not found");
        }
        return optional.get();
    }


//    public GoodsEntity getGoodsById(Long id) throws GoodsNotFoundException {
//        Optional<GoodsEntity> optional = goodsRepository.findById(id);
//        if (optional.isEmpty()) {
//            throw new GoodsNotFoundException("Product not found");
//        }
//        GoodsEntity goods = optional.get();
//        SubcategoriesGoodsEntity subcategory = goods.getSubcategoriesGoods(); // Отримати підкатегорію
//        goods.setSubcategoriesGoods(subcategory); // Встановити підкатегорію знову для уникнення циклічних посилань
////        System.out.println("subcategory: " + goods.getSubcategoriesGoods().getName());
//        return goods;
//    }


    public GoodsGetAllDTO entityToDTO(GoodsEntity entity) {

        GoodsGetAllDTO dto = new GoodsGetAllDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        dto.setShort_discription(entity.getShort_discription());

        dto.setSubcategoryId(subcategoriesGoodsService.entityToDTO(entity.getSubcategoriesGoods()).getId());
        dto.setSubcategoriesGoodsName(subcategoriesGoodsService.entityToDTO(entity.getSubcategoriesGoods()).getName());
        dto.setPhotosGoodsDTOS(photosGoodsService.entityListToDTOS(entity.getPhotosGoods()));
        return dto;
    }
//    public GoodsForCatalogDTO entityToCatalogDTO(GoodsEntity entity) {
//
//        GoodsForCatalogDTO dto = new GoodsForCatalogDTO();
//        dto.setId(entity.getId());
//        dto.setName(entity.getName());
//
//        dto.setSubcategoryId(subcategoriesGoodsService.entityToDTO(entity.getSubcategoriesGoods()).getId());
//
//        dto.setSubcategoryName(subcategoriesGoodsService.entityToDTO(entity.getSubcategoriesGoods()).getName());
//        return dto;
//    }

//    public List<GoodsGetAllDTO> getAll() {
//        Iterable<GoodsEntity> goodsIterable = goodsRepository.findAll();
//        List<GoodsEntity> goodsList = new ArrayList<>();
//        goodsIterable.forEach(goodsList::add);
//
//        List<GoodsGetAllDTO> dtoList = new ArrayList<>();
//        for (GoodsEntity entity : goodsList) {
//            dtoList.add(entityToDTO(entity));
//        }
//        return dtoList;
//    }
//    public ResponseEntity<List<GoodsEntity>> getAll1() {
//        Iterable<GoodsEntity> goodsIterable = goodsRepository.findAll();
//        List<GoodsEntity> goodsList = new ArrayList<>();
//        goodsIterable.forEach(goodsList::add);
//        return new ResponseEntity<>(goodsList, HttpStatus.OK);
//    }

    //    public Long delete(Long id){
//        goodsRepository.deleteById(id);
//        return id;
//    }
//    @Transactional
//    public void deletePhotosByGoodsId(Long goodsId){
//        //Видалення зображень товару за допомогою ID товару
//        photosGoodsRepository.deleteByGoods_Id(goodsId);
//    }
    @Transactional
    public void delete(Long id) {
        // Видалення фотографій товару за його ідентифікатором
        photosGoodsRepository.deleteByGoodsId(id);
        goodsRepository.deleteById(id);
    }
}
