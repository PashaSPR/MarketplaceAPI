package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.OrdersListsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersListsRepository extends JpaRepository<OrdersListsEntity,Long> {
    OrdersListsEntity findByNumber (String number);//по номеру замовлення
    //OrdersListsEntity findById (Long id);//по id замовлення
}
