package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.PhotosGoodsToSaveDTO;
import com.example.webshopdip.entities.PhotosGoodsEntity;
import com.example.webshopdip.exceptions.GoodsNotFoundException;
import com.example.webshopdip.repositories.GoodsRepository;
import com.example.webshopdip.services.PhotosGoodsService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/photosGoods")
@CrossOrigin
public class PhotosGoodsController {

//    @Autowired
//    private PhotosGoodsService photosGoodsService;
//
//    @PostMapping
//    public ResponseEntity addPhotosGoods(@RequestBody PhotosGoodsDTO photosGoodsDTO, @RequestParam Long goodsId) {
//        try {
//            photosGoodsService.addPhotosGoods(photosGoodsDTO, goodsId);
//            return ResponseEntity.ok("Нова категорія товару успішно додана");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Виникла помилка");
//        }
//    }

    private final PhotosGoodsService photosGoodsService;
    //    @Autowired
    private GoodsRepository goodsRepository;

    public PhotosGoodsController(PhotosGoodsService photosGoodsService) {
        this.photosGoodsService = photosGoodsService;
    }

//    @PostMapping
//    public ResponseEntity<PhotosGoodsEntity> addPhoto(
//            @RequestBody PhotosGoodsDTO photoDTO,
//            @RequestParam Long goodsId
//    ) {
//        GoodsEntity goodsEntity = goodsRepository.findById(goodsId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid goodsId"));
//        PhotosGoodsEntity savedPhoto = photosGoodsService.addPhotosGoods(photoDTO, goodsEntity);
//        return ResponseEntity.ok(savedPhoto);
//    }

//    @PostMapping
//    public ResponseEntity<PhotosGoodsEntity> addPhotosGoods(@RequestBody PhotosGoodsDTO photosGoodsDTO) throws GoodsNotFoundException, IOException {
//        PhotosGoodsEntity savedPhotosGoods = photosGoodsService.savePhotosGoods(photosGoodsDTO);
//        return ResponseEntity.ok(savedPhotosGoods);
//    }


//    @PostMapping
//    public ResponseEntity<PhotosGoodsEntity> addPhotosGoods(@RequestParam("file") MultipartFile file,
//                                                            @RequestParam("description") String description, @RequestParam("goodsId") Long goodsId)
//            throws GoodsNotFoundException, IOException {
//        PhotosGoodsDTO photosGoodsDTO = new PhotosGoodsDTO();
//        photosGoodsDTO.setDescription(description);
//        photosGoodsDTO.setGoodsId(goodsId);
//
//        // Додаткова обробка файлу, якщо потрібно
//        // ...
//
//        PhotosGoodsEntity savedPhotosGoods = photosGoodsService.savePhotosGoods(photosGoodsDTO);
//        return ResponseEntity.ok(savedPhotosGoods);
//    }




//    @PostMapping
//    public ResponseEntity<PhotosGoodsEntity> addPhotosGoods(@RequestParam("file") MultipartFile file,
//                                                            @RequestParam("description") String description,
//                                                            @RequestParam("goodsId") Long goodsId)
//            throws GoodsNotFoundException, IOException {
//        PhotosGoodsDTO photosGoodsDTO = new PhotosGoodsDTO();
//        photosGoodsDTO.setDescription(description);
//        photosGoodsDTO.setGoodsId(goodsId);
//        photosGoodsDTO.setPath(file.getOriginalFilename()); // Встановлюємо шлях до файлу
//
//        // Збереження файлу на сервері або копіювання в потрібну директорію
//        // ...
//
//        PhotosGoodsEntity savedPhotosGoods = photosGoodsService.savePhotosGoods(photosGoodsDTO);
//        return ResponseEntity.ok(savedPhotosGoods);
//    }

    @PostMapping
    public ResponseEntity<PhotosGoodsEntity> addPhotosGoods(@RequestParam("file") MultipartFile file,
                                                            @RequestParam("description") String description,
                                                            @RequestParam("goodsId") Long goodsId)
            throws GoodsNotFoundException, IOException {
        PhotosGoodsToSaveDTO photosGoodsToSaveDTO = new PhotosGoodsToSaveDTO();
        photosGoodsToSaveDTO.setDescription(description);
        photosGoodsToSaveDTO.setGoodsId(goodsId);

        PhotosGoodsEntity savedPhotosGoods = photosGoodsService.savePhotosGoods(photosGoodsToSaveDTO, file);
        return ResponseEntity.ok(savedPhotosGoods);
    }

    @DeleteMapping("/{photoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long photoId) {
        photosGoodsService.deletePhoto(photoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("deleteByGoods/{goodId}")
    public ResponseEntity<Void> deletePhotoByGoodsId(@PathVariable Long goodId) {
        photosGoodsService.deletePhotosByGoodsId(goodId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/goods/{goodsId}")
//    public ResponseEntity<List<PhotosGoodsEntity>> getPhotosByGoodsId(@PathVariable Long goodsId) {
//        List<PhotosGoodsEntity> photos = photosGoodsService.getPhotosByGoodsId(goodsId);
//        return ResponseEntity.ok(photos);
//    }


    //    @GetMapping("/goods/{id}/photos")
//    public ResponseEntity<List<PhotosGoodsEntity>> getPhotosByGoodsId(@PathVariable("id") Long goodsId) {
//        List<PhotosGoodsEntity> photos = photosGoodsService.getPhotosByGoodsId(goodsId);
//        return new ResponseEntity<>(photos, HttpStatus.OK);
//    }
    @GetMapping("/goods/{id}/photos")
    public ResponseEntity<List<PhotosGoodsEntity>> getPhotosByGoodsId(@PathVariable("id") Long goodsId, HttpServletRequest request) {
//    String currentUrl = request.getRequestURL().toString();
        String currentUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        List<PhotosGoodsEntity> photos = photosGoodsService.getPhotosByGoodsId(goodsId, currentUrl);
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }
}
