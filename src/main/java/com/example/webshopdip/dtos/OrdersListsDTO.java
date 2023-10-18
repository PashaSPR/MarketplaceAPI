package com.example.webshopdip.dtos;

import com.example.webshopdip.entities.CustomersEntity;
import com.example.webshopdip.entities.DeliveriesMethodEntity;
import com.example.webshopdip.entities.GoodsOrdersEntity;
import com.example.webshopdip.entities.PaymentsTypeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class OrdersListsDTO {
    private Long id; // Унікальний ідентифікатор категорії товару
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

    public OrdersListsDTO(Long id, String number, CustomersEntity customers, PaymentsTypeEntity paymentsType, DeliveriesMethodEntity deliveriesMethod, String address_delivery, List<GoodsOrdersEntity> goodsOrders) {
        this.id = id;
        this.number = number;
        this.customers = customers;
        this.paymentsType = paymentsType;
        this.deliveriesMethod = deliveriesMethod;
        this.address_delivery = address_delivery;
        this.goodsOrders = goodsOrders;
    }
    public OrdersListsDTO() {
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

    public DeliveriesMethodEntity getDeliveriesMethod() {
        return deliveriesMethod;
    }

    public String getAddress_delivery() {
        return address_delivery;
    }

    public List<GoodsOrdersEntity> getGoodsOrders() {
        return this.goodsOrders;
    }

}

