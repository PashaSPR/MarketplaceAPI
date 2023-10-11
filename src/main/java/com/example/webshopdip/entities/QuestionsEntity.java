package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import java.time.LocalDate;

/**
 * Клас, що представляє сутність "Запитання щодо певних Товарів".
 * Містить дані про Запитання щодо певних Товарів від покупців.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "questions")
public class QuestionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Запитання
    private LocalDate date_question; // Дата Запитання
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "usersLists_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Запитання(відповідей) може бути від одного Користувача
    private UsersListsEntity usersLists;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Запитань(відповідей) може стосуватися одного Товару
    private GoodsEntity goods;
    private String question; // Запитання щодо певного Товару
}
