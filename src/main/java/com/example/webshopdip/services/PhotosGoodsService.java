package com.example.webshopdip.services;

import com.example.webshopdip.dtos.PhotosGoodsDTO;
import com.example.webshopdip.dtos.PhotosGoodsToSaveDTO;
import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.entities.PhotosGoodsEntity;
import com.example.webshopdip.exceptions.GoodsNotFoundException;
import com.example.webshopdip.repositories.GoodsRepository;
import com.example.webshopdip.repositories.PhotosGoodsRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotosGoodsService {
//    @Autowired
//    private PhotosGoodsRepository photosGoodsRepository;
//    public PhotosGoodsEntity addPhotosGoods (PhotosGoodsEntity photosGoodsEntity) {
//
//        PhotosGoodsEntity photo = new PhotosGoodsEntity();
//        String photoPath = savePhotoOnServer(photoFile); // Збереження фотофайлу на сервері та отримання шляху
//        photo.setPath(photoPath);
//        photo.setGoods(savedGoods);
//        photosGoodsRepository.save(photo);
//
//
//
//
//
//        return photosGoodsRepository.save(photosGoodsEntity);
//    }
//


    @Autowired
    private final PhotosGoodsRepository photosGoodsRepository;
    @Autowired
    private final GoodsRepository goodsRepository;
//    private final GoodsService goodsService;

//    public PhotosGoodsService(PhotosGoodsRepository photosGoodsRepository, GoodsService goodsService) {
//        this.photosGoodsRepository = photosGoodsRepository;
//        this.goodsService = goodsService;
//    }

    public PhotosGoodsService(PhotosGoodsRepository photosGoodsRepository, GoodsRepository goodsRepository) {
        this.photosGoodsRepository = photosGoodsRepository;
        this.goodsRepository = goodsRepository;
    }

//    public PhotosGoodsEntity addPhotosGoods(PhotosGoodsDTO photoDTO, GoodsEntity goodsEntity) {
//        PhotosGoodsEntity photo = new PhotosGoodsEntity();
//        photo.setDescription(photoDTO.getDescription());
//        photo.setPath(photoDTO.getPath());
//        photo.setGoods(goodsEntity);
//        return photosGoodsRepository.save(photo);
//    }

//    public PhotosGoodsEntity savePhotosGoods(PhotosGoodsDTO photosGoodsDTO) throws GoodsNotFoundException {
//        PhotosGoodsEntity photosGoods = new PhotosGoodsEntity();
//        photosGoods.setDescription(photosGoodsDTO.getDescription());
//        photosGoods.setPath(photosGoodsDTO.getPath());
//
//        Long goodsId = photosGoodsDTO.getDoodsId();
//        if (goodsId == null) {
//            throw new IllegalArgumentException("goodsId must not be null");
//        }
//
//        GoodsEntity goods = goodsService.getGoodsById(goodsId);
//        photosGoods.setGoods(goods);
//
//        return photosGoodsRepository.save(photosGoods);
//    }
/////////////////////////////////////////////////////////////////////////////////////////
//знизу попередній код додавання зображень - РОБОЧИЙ лише через POSTMAN//
//
//    public class FileUtil {
//        public static String generateRandomFileName(String fileExtension) {
//            String fileName = UUID.randomUUID().toString();
//            return fileName + fileExtension;
//        }
//    }
//    public PhotosGoodsEntity savePhotosGoods(PhotosGoodsDTO photosGoodsDTO) throws GoodsNotFoundException, IOException {
//        PhotosGoodsEntity photosGoods = new PhotosGoodsEntity();
//        photosGoods.setDescription(photosGoodsDTO.getDescription());
//
//        String fileExtension = getFileExtension(photosGoodsDTO.getPath());
//        String randomFileName = FileUtil.generateRandomFileName(fileExtension);
//
//        // Збереження файлу в папку проекту
//        String uploadDirectory = "public\\images"; // Вкажіть шлях до папки для завантаження
//        Path path = Paths.get(uploadDirectory);
//        if (!Files.exists(path)) {
//            Files.createDirectories(path);
//        }
//        String fileName = randomFileName;
//        String filePath = uploadDirectory + File.separator + fileName;
//
//        // Копіювання файлу в папку проекту
//        copyFile(photosGoodsDTO.getPath(), filePath);
//
//        photosGoods.setPath(fileName);
//
//        Long goodsId = photosGoodsDTO.getGoodsId();
//        if (goodsId == null) {
//            throw new IllegalArgumentException("goodsId must not be null");
//        }
//
//        GoodsEntity goods = goodsService.getGoodsById(goodsId);
//        photosGoods.setGoods(goods);
//
//        return photosGoodsRepository.save(photosGoods);
//    }
//
//    private String getFileExtension(String fileName) {
//        if (fileName != null && fileName.lastIndexOf(".") != -1) {
//            return fileName.substring(fileName.lastIndexOf("."));
//        }
//        return "";
//    }
//
//    private void copyFile(String sourceFilePath, String destinationFilePath) {
//        try {
//            Files.copy(Paths.get(sourceFilePath), Paths.get(destinationFilePath), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to copy file: " + e.getMessage());
//        }
//    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////
    //новий код - експеримент
    private final String uploadDirectory = "public/images"; // Шлях до папки для завантаження

    public PhotosGoodsEntity savePhotosGoods(PhotosGoodsToSaveDTO photosGoodsToSaveDTO, MultipartFile file) throws GoodsNotFoundException, IOException {
        PhotosGoodsEntity photosGoods = new PhotosGoodsEntity();
        photosGoods.setDescription(photosGoodsToSaveDTO.getDescription());

        String fileExtension = getFileExtension(file.getOriginalFilename());
        String randomFileName = generateRandomFileName(fileExtension);

        Path path = Paths.get(uploadDirectory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        String fileName = randomFileName;
        String filePath = uploadDirectory + "/" + fileName;

        Files.copy(file.getInputStream(), Paths.get(filePath));

        photosGoods.setPath(fileName);

        Long goodsId = photosGoodsToSaveDTO.getGoodsId();
        if (goodsId == null) {
            throw new IllegalArgumentException("goodsId must not be null");
        }
// //////////////////////////////2023.08.23//////////////////////////////////////////////////////////////////////////
//        GoodsEntity goods = goodsService.getGoodsById(goodsId);
//        photosGoods.setGoods(goods);


        GoodsEntity goodsEntity;
        Optional<GoodsEntity> optionalGoodsEntity = goodsRepository.findById(goodsId);
        if (optionalGoodsEntity.isPresent()) {
            goodsEntity = optionalGoodsEntity.get();
        } else {
            goodsEntity = new GoodsEntity();
        }
        photosGoods.setGoods(goodsEntity);

        return photosGoodsRepository.save(photosGoods);
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String generateRandomFileName(String fileExtension) {
        return UUID.randomUUID().toString() + "." + fileExtension;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public void deletePhoto(Long photoId) {
        photosGoodsRepository.deleteById(photoId);
    }


    @Transactional
    public void deletePhotosByGoodsId(Long goodId) {
        photosGoodsRepository.deleteByGoodsId(goodId);
    }

    //    public List<PhotosGoodsEntity> getPhotosByGoodsId(Long goodsId) {
//        return photosGoodsRepository.findByGoodsId(goodsId);
//    }
    public List<PhotosGoodsEntity> getPhotosByGoodsId(Long goodsId, String currentUrl) {
        List<PhotosGoodsEntity> photos = photosGoodsRepository.findByGoodsId(goodsId);
        for (PhotosGoodsEntity photo : photos) {
            String imageUrl = currentUrl + "/images/" + photo.getPath();
            photo.setPath(imageUrl);
        }
        return photos;
    }
    public PhotosGoodsDTO entityToDTO(PhotosGoodsEntity entity) {
        PhotosGoodsDTO dto = new PhotosGoodsDTO();
        dto.setId(entity.getId());
        dto.setPath(entity.getPath());
        dto.setDescription(entity.getDescription());

        return dto;
    }
    public List<PhotosGoodsDTO> entityListToDTOS(List<PhotosGoodsEntity> entityList) {
        List<PhotosGoodsDTO> dtos = new ArrayList<>();
        for (PhotosGoodsEntity entity: entityList){
            dtos.add(entityToDTO(entity));
        }
        return dtos;
    }
}
