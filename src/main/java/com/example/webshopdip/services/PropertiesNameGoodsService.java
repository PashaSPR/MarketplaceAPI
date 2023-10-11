//package com.example.mayprojegtdip.services;
//
//import com.example.mayprojegtdip.entities.PropertiesNameGoodsEntity;
//import com.example.mayprojegtdip.exceptions.PropertiesNameGoodsAlreadyExistException;
//import com.example.mayprojegtdip.exceptions.PropertiesNameGoodsNotFoundException;
//import com.example.mayprojegtdip.exceptions.SubcategoriesGoodsAlreadyExistException;
//import com.example.mayprojegtdip.repo.PropertiesNameGoodsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//@Service
//public class PropertiesNameGoodsService {
//    @Autowired
//    private PropertiesNameGoodsRepository propertiesNameGoodsRepository;
//    public PropertiesNameGoodsEntity createPropertiesNameGoods(PropertiesNameGoodsEntity propertiesNameGoodsEntity) throws PropertiesNameGoodsAlreadyExistException {
//        if (propertiesNameGoodsRepository.findByName(propertiesNameGoodsEntity.getName()) != null) {
//            throw new PropertiesNameGoodsAlreadyExistException("Така Властивість вже існує");
//        }
//        return propertiesNameGoodsRepository.save(propertiesNameGoodsEntity);
//    }
//    public PropertiesNameGoodsEntity getOne(Long id) throws PropertiesNameGoodsNotFoundException {
//        Optional<PropertiesNameGoodsEntity> optional = propertiesNameGoodsRepository.findById(id);
//        if(optional.isEmpty()){
//            throw new PropertiesNameGoodsNotFoundException("Властивості товару не знайдено");
//        }
//        return optional.get();
//    }
//    public void deletePropertiesNameGoods(Long id) {
//        propertiesNameGoodsRepository.deleteById(id);
//    }
//}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.example.webshopdip.services;

import com.example.webshopdip.dtos.PropertiesNameGoodsDTO;
import com.example.webshopdip.dtos.PropertiesNameGoodsGetAllDTO;
import com.example.webshopdip.entities.PropertiesNameGoodsEntity;
import com.example.webshopdip.exceptions.PropertiesNameGoodsAlreadyExistException;
import com.example.webshopdip.exceptions.PropertiesNameGoodsNotFoundException;
import com.example.webshopdip.repositories.PropertiesNameGoodsRepository;
import com.example.webshopdip.repositories.SubcategoriesGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertiesNameGoodsService {

    @Autowired
    private PropertiesNameGoodsRepository propertiesNameGoodsRepository;
    @Autowired
    private SubcategoriesGoodsRepository subcategoriesGoodsRepository;

    public PropertiesNameGoodsDTO createPropertiesNameGoods(PropertiesNameGoodsDTO dto) throws PropertiesNameGoodsAlreadyExistException {
        if (propertiesNameGoodsRepository.findByName(dto.getName()) != null) {
            throw new PropertiesNameGoodsAlreadyExistException("Така Властивість вже існує");
        }

        PropertiesNameGoodsEntity entity = new PropertiesNameGoodsEntity();
        entity.setName(dto.getName());
        entity.setValueType(dto.getValueType());
//        entity.setSubcategoriesGoodsEntities(subcategoriesGoodsRepository.findById(dto.getSubcategoriesGoodsId()).orElse(null) );
        entity = propertiesNameGoodsRepository.save(entity);
        return entityToDTO(entity);
    }

//    public PropertiesNameGoodsDTO entityToDTO(PropertiesNameGoodsEntity entity) {
////        PropertiesNameGoodsDTO dto = new PropertiesNameGoodsDTO();
////        dto.setId(entity.getId());
////        dto.setName(entity.getName());
//////        System.out.println("getSubcategoriesGoods ID: " + entity.getSubcategoriesGoods().getId());
////        dto.setSubcategoriesGoodsId(entity.getSubcategoriesGoods().getId());
////
////        return dto;
//        PropertiesNameGoodsDTO dto = new PropertiesNameGoodsDTO();
//        dto.setId(entity.getId());
//        dto.setName(entity.getName());
//
//        SubcategoriesGoodsEntity subcategoriesGoods = entity.getSubcategoriesGoods();
//        if (subcategoriesGoods != null) {
//            dto.setSubcategoriesGoodsId(subcategoriesGoods.getId());
//        } else {
//            dto.setSubcategoriesGoodsId(null); // або будь-яке значення, яке ви вважаєте за потрібне
//        }
//
//        return dto;
//    }


    public PropertiesNameGoodsDTO entityToDTO(PropertiesNameGoodsEntity entity) {
        PropertiesNameGoodsDTO dto = new PropertiesNameGoodsDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

//        List<SubcategoriesGoodsDTO> subcategoriesDTOs = new ArrayList<>();
//        for (SubcategoriesGoodsEntity subcategoriesGoods : entity.getSubcategoriesGoodsEntities()) {
//            SubcategoriesGoodsDTO subcategoriesDTO = new SubcategoriesGoodsDTO();
//            subcategoriesDTO.setId(subcategoriesGoods.getId());
//            // Set other fields in the subcategoriesDTO if needed
//            subcategoriesDTOs.add(subcategoriesDTO);
//        }
//        dto.setSubcategoriesGoodsDTOS(subcategoriesDTOs);

        return dto;
    }

    public PropertiesNameGoodsGetAllDTO entityToDTOGetAll(PropertiesNameGoodsEntity entity) {
        PropertiesNameGoodsGetAllDTO dto = new PropertiesNameGoodsGetAllDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setValueType(entity.getValueType());
        return dto;
    }


    public PropertiesNameGoodsDTO getOne(Long id) throws PropertiesNameGoodsNotFoundException {
        Optional<PropertiesNameGoodsEntity> optional = propertiesNameGoodsRepository.findById(id);

        if (optional.isEmpty()) {
            throw new PropertiesNameGoodsNotFoundException("Властивості товару не знайдено");
        }

        PropertiesNameGoodsEntity entity = optional.get();
        return entityToDTO(entity);
    }

    public void deletePropertiesNameGoods(Long id) {
        propertiesNameGoodsRepository.deleteById(id);
    }

    // Other methods...
}
