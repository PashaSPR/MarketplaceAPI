package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Покупці".
 * Містить дані та поведінку, пов'язані з Покупцем.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "customers")
public class CustomersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Покупця
    private String name; // Ім'я(повне) зареєстрованого Покупця
    private String address; // Адреса проживання зареєстрованого Покупця
    private String phone; // Телефон зареєстрованого Покупця
    private String email; // Електронна скринька зареєстрованого Покупця
    @OneToOne
    @JoinColumn(name = "usersLists_id", referencedColumnName = "id")
    // Зв'язок One-to-One: Один Покупець відповідає одному Користувачу
    private UsersListsEntity usersLists;

    /////////сутності що мають відношення Many-to-One з сутністю Customers
    @OneToMany(mappedBy = "customers")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один Покупець може мати багато Переглядів різних товарів
    private List<HistoryViewsGoodsEntity> historyViewsGoods = new ArrayList<>();
    @OneToMany(mappedBy = "customers")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один Покупець може мати багато Замовлень товарів
    private List<OrdersListsEntity> ordersLists = new ArrayList<>();
}
