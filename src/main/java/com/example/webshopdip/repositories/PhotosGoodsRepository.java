//package com.example.mayprojegtdip.repo;
//
//import com.example.mayprojegtdip.entities.CategoriesGoodsEntity;
//import com.example.mayprojegtdip.entities.PhotosGoodsEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//
//import java.util.List;
//
//public interface PhotosGoodsRepository extends JpaRepository<PhotosGoodsEntity, Long> {
//    PhotosGoodsEntity findByPath (String name);
//    List<PhotosGoodsEntity> findByGoodsId (Long goodsId); // чи потрібно?
//}

package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.PhotosGoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotosGoodsRepository extends JpaRepository<PhotosGoodsEntity, Long> {
    PhotosGoodsEntity findByPath(String path);
    //    @Modifying
    //    @Query("DELETE FROM PhotosGoodsEntity pg WHERE pg.goods.id = :goodsId")
    //    void deleteByGoods_Id(@Param("goodsId") Long goodsId);
    //    List<PhotosGoodsEntity> findByGoodsId(Long goodsId);
    List<PhotosGoodsEntity> findByGoodsId(Long goodsId);
    void deleteByGoodsId(Long goodsId);
}