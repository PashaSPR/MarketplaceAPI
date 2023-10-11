package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;

import java.time.LocalDateTime;

/**
 * Клас, що представляє сутність "Історія Переглядів Товарів".
 * Містить дані про Історію Переглядів Товарів покупцем.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "historyviewsgoods")
public class HistoryViewsGoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Переглядів товару
    private LocalDateTime viewed_at; // Дата Перегляду товару
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Переглядів товару може стосуватися Покупця
    private CustomersEntity customers;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Переглядів товару може стосуватися Товару
    private GoodsEntity goods ;
}
