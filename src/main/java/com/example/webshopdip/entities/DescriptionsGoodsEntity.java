package com.example.webshopdip.entities;

import javax.persistence.*;
import org.attoparser.dom.Text;

/**
 * Клас, що представляє сутність "Повний Опис товару".
 * Містить дані про Повний Опис товару.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "descriptionsgoods")
public class DescriptionsGoodsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Опису товару
    private Text description; // Повний Опис товару
}
