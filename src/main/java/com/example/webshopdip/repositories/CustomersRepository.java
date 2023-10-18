package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.CategoriesGoodsEntity;
import com.example.webshopdip.entities.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<CustomersEntity,Long> {
        CustomersEntity findByName (String name);

    }
