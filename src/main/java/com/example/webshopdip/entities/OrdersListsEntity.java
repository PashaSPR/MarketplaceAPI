package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Замовлення на Товари".
 * Містить Замовлення на Товари покупців.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "orderslists")
public class OrdersListsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Замовлення на Товари
    private String number; //Номер Замовлення на Товари
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Замовлень на Товари може стосуватися одного Продавця
    private CustomersEntity customers;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "paymentsType_id", referencedColumnName = "id")
    // Зв'язок One-to-One: Багато Замовлень на Товари відповідає одному Типу оплати за товар
    private PaymentsTypeEntity paymentsType;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "deliveriesMethod_id", referencedColumnName = "id")
    // Зв'язок One-to-One: Багато Замовлень на Товари відповідає одному Методу доставлення товару
    private DeliveriesMethodEntity deliveriesMethod;
    private String address_delivery;  // Адреса доставлення товару

    /////////сутності що мають відношення One-to-Many з сутністю OrdersLists
    @OneToMany(mappedBy = "ordersLists")
    @JsonManagedReference
    // Зв'язок One-to-Many: Одне Замовлення на Товари може мати багато Переліків Товарів
    private List<GoodsOrdersEntity> goodsOrders = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCustomers(CustomersEntity customers) {
        this.customers = customers;
    }

    public void setPaymentsType(PaymentsTypeEntity paymentsType) {
        this.paymentsType = paymentsType;
    }

    public void setDeliveriesMethod(DeliveriesMethodEntity deliveriesMethod) {
        this.deliveriesMethod = deliveriesMethod;
    }

    public void setAddress_delivery(String address_delivery) {
        this.address_delivery = address_delivery;
    }

    public void setGoodsOrders(List<GoodsOrdersEntity> goodsOrders) {
        this.goodsOrders = goodsOrders;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public CustomersEntity getCustomers() {
        return customers;
    }

    public PaymentsTypeEntity getPaymentsType() {
        return paymentsType;
    }

    public DeliveriesMethodEntity getDeliveriesMethod() {
        return deliveriesMethod;
    }

    public String getAddress_delivery() {
        return address_delivery;
    }

    public List<GoodsOrdersEntity> getGoodsOrders() {
        return goodsOrders;
    }
}
