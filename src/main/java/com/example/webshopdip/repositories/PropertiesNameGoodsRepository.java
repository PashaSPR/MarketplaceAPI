package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.PropertiesNameGoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertiesNameGoodsRepository extends JpaRepository<PropertiesNameGoodsEntity,Long> {
    PropertiesNameGoodsEntity findByName(String name);
}
