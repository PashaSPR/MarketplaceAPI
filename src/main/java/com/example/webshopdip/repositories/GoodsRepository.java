package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//@Repository
public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {
//    @Query("SELECT g FROM GoodsEntity g WHERE g.name = :name")
//    GoodsEntity findBayName(String name);
    GoodsEntity findByName(String name);
    List<GoodsEntity> findBySubcategoriesGoods_CategoriesGoods_Id(Long id);
    List<GoodsEntity> findBySubcategoriesGoods_Id(Long id);
    //    GoodsEntity getAll();
}
