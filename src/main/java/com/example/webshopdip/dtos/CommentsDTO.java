package com.example.webshopdip.dtos;

import java.time.LocalDate;

public class CommentsDTO {
    private LocalDate date_comment; // Дата створення коменту до товару
    private Long usersListsId;

    private Long goodsId;
    private String comment; // Коментар до товару

    public LocalDate getDate_comment() {
        return date_comment;
    }

    public void setDate_comment(LocalDate date_comment) {
        this.date_comment = LocalDate.now();
    }

    public void setUsersListsId(Long usersListsId) {
        this.usersListsId = usersListsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getUsersListsId() {
        return usersListsId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
