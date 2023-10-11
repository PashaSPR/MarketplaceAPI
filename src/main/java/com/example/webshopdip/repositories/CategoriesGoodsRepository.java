package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.CategoriesGoodsEntity;
import com.example.webshopdip.entities.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesGoodsRepository extends JpaRepository<CategoriesGoodsEntity,Long> {
    CategoriesGoodsEntity findByName (String name);
}
