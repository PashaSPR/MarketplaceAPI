package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {
    List<CommentsEntity> findByGoodsId(Long goodsId);
}
