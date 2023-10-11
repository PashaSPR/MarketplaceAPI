package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.PropertiesGoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertiesGoodsRepository extends JpaRepository<PropertiesGoodsEntity, Long> {
    List<PropertiesGoodsEntity> findByGoodsId(Long goodsId);
}
