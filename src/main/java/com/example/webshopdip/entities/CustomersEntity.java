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

    public CustomersEntity(Long id, String name, String address, String phone, String email, UsersListsEntity usersLists, List<HistoryViewsGoodsEntity> historyViewsGoods, List<OrdersListsEntity> ordersLists) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.usersLists = usersLists;
        this.historyViewsGoods = historyViewsGoods;
        this.ordersLists = ordersLists;
    }

    public CustomersEntity() {
    }

    @OneToMany(mappedBy = "customers")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один Покупець може мати багато Замовлень товарів
    private List<OrdersListsEntity> ordersLists = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsersListsEntity getUsersLists() {
        return usersLists;
    }

    public void setUsersLists(UsersListsEntity usersLists) {
        this.usersLists = usersLists;
    }

    public List<HistoryViewsGoodsEntity> getHistoryViewsGoods() {
        return historyViewsGoods;
    }

    public void setHistoryViewsGoods(List<HistoryViewsGoodsEntity> historyViewsGoods) {
        this.historyViewsGoods = historyViewsGoods;
    }

    public List<OrdersListsEntity> getOrdersLists() {
        return ordersLists;
    }

    public void setOrdersLists(List<OrdersListsEntity> ordersLists) {
        this.ordersLists = ordersLists;
    }
}
