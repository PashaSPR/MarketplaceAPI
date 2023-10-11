package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Оцінка Товарів".
 * Містить дані Оцінку товарів, надану покупцями,які придбали даний товар.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "evaluationsgood")
public class EvaluationsGoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Оцінки товарів
    private Integer evaluation; // Оцінка Товару
    /////////сутності що мають відношення Many-to-One з сутністю Customers
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато оцінок може належати одному товару
    private GoodsEntity goods;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Покупців можуть поставити одну оцінку на різні товарів
    private CustomersEntity customers;
}
