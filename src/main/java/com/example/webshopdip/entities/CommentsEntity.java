package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

import java.time.LocalDate;

/**
 * Клас, що представляє сутність "Коментарі Товарів".
 * Містить Коментарі, що надають покупці щодо певного товару.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "comments")
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор коменту
    private LocalDate date_comment; // Дата створення коменту до товару
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "userslists_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Коментів може належити одному Користувачу
    private UsersListsEntity usersLists;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Коментів може стосуватися одного Товару
    private GoodsEntity goods;
    private String comment; // Коментар до товару

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate_comment() {
        return date_comment;
    }

    public void setDate_comment(LocalDate date_comment) {
        this.date_comment = date_comment;
    }

    public UsersListsEntity getUsersLists() {
        return usersLists;
    }

    public void setUsersLists(UsersListsEntity usersLists) {
        this.usersLists = usersLists;
    }

    public GoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(GoodsEntity goods) {
        this.goods = goods;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
