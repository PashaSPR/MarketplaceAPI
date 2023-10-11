package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import java.time.LocalDate;

/**
 * Клас, що представляє сутність "Знижки Товарів".
 * Містить дані щодо Знижок на певний товар.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "discounts")
public class DiscountsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Знижки на товар
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Знижок може стосуватися одного Товару
    private GoodsEntity goods;
    private String name; // Назва Знижки на товар
    private String description; // Опис Знижки на товар
    private LocalDate date_start; // Дата, з якої стартує Знижка на товар
    private LocalDate date_end; // Кінцева дата Знижка на товар
    private Integer value; // Значення Знижки у відсотках
}
