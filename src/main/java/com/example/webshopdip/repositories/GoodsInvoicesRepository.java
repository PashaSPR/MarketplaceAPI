package com.example.webshopdip.repositories;

import com.example.webshopdip.dtos.GoodsGetAllDTO;
import com.example.webshopdip.entities.GoodsInvoicesEntity;
import com.example.webshopdip.dtos.GoodsInvoicesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsInvoicesRepository extends JpaRepository<GoodsInvoicesEntity, Long> {
    // Пошук за категорією
    List<GoodsInvoicesDTO> findByGoods_SubcategoriesGoodsName(String subcategoriesGoodsName);

    List<GoodsInvoicesEntity> findByGoodsSubcategoriesGoodsCategoriesGoodsName(String categoriesGoodsName);

    List<GoodsInvoicesEntity> findByGoodsSubcategoriesGoodsCategoriesGoods_Id(Long id);

//    List<GoodsInvoicesEntity> findByGoodsSubca(Long id);

    // Пошук за категорією і підкатегорією
    List<GoodsInvoicesDTO> findByGoods_SubcategoriesGoodsNameAndGoods_name(String subcategoriesGoodsName, String name);


    // Пошук за категорією і підкатегорією і вивід у GoodsInvoicesDTO


//    @Query("SELECT new com.example.webshopdip.dtos.GoodsInvoicesDTO(gi.id, g.name, g.descriptionsGoods, gi.price, gi.quantity) " +
//            "FROM GoodsInvoicesEntity gi " +
//            "JOIN gi.goods g " +
//            "WHERE g.subcategoriesGoods = :subcategoriesGoodsName AND g.name = :name")
//    List<GoodsInvoicesDTO> findBySubcategoriesAndName(String subcategoriesGoodsName, String name);

    //Long id; GoodsGetAllDTO goods; Float price; Integer quantity;

    @Query("SELECT gi " +
            "FROM GoodsInvoicesEntity gi " +
            "JOIN gi.goods g " +
            "JOIN g.subcategoriesGoods sg " +
//            "JOIN sg.categoriesGoods cg " +
            "WHERE g.subcategoriesGoods.categoriesGoods.id = :categoryId")
    List<GoodsInvoicesEntity> findGoodsByCategoryId(Long categoryId);

    @Query("SELECT gi " +
            "FROM GoodsInvoicesEntity gi " +
            "WHERE gi.goods.subcategoriesGoods.id = :subcategoryId")
    List<GoodsInvoicesEntity> findGoodsBySubcategoryId(Long subcategoryId);


    @Query("SELECT gi " +
            "FROM GoodsInvoicesEntity gi " +
            "WHERE gi.goods.id = :goodId")
    List<GoodsInvoicesEntity> findByGoodsId(Long goodId);

//    List<GoodsInvoicesEntity> findByGoodsId(Long id);

//    @Query("SELECT g FROM GoodsInvoicesEntity g WHERE g.goodsSubcategoriesGoods.categoriesGoods.id = :categoryId")
//    List<Goods> findGoodsByCategoryId(@Param("categoryId") Long categoryId);
}

