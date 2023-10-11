package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.GoodsInvoicesEntity;
import com.example.webshopdip.entities.SubcategoriesGoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SubcategoriesGoodsRepository extends JpaRepository<SubcategoriesGoodsEntity, Long> {
    SubcategoriesGoodsEntity findByName(String name);

//    @Query("SELECT s " +
//            "FROM SubcategoriesGoodsEntity s " +
//            "WHERE s.goods.id = :goodId")
//    GoodsInvoicesEntity findByGoodsId1(Long goodId);
//
//    @Query("SELECT s FROM SubcategoriesGoodsEntity s WHERE s.goods.id = :goodId")
//    GoodsInvoicesEntity findByGoodsId(@Param("goodId") Long goodId);
    List<SubcategoriesGoodsEntity> findByCategoriesGoods_Id(Long id);
}

//@Repository
//public interface SubcategoriesGoodsRepository extends JpaRepository<SubcategoriesGoodsEntity, Long> {
//}
