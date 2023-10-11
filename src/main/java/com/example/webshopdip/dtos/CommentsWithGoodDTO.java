package com.example.webshopdip.dtos;

import com.example.webshopdip.entities.UsersListsEntity;

import java.time.LocalDate;

public class CommentsWithGoodDTO {
    private Long id;
    private LocalDate date_comment; // Дата створення коменту до товару
    private UserListsDTO usersListsId;
    private GoodsGetAllDTO good;
    private String comment; // Коментар до товару

    public CommentsWithGoodDTO() {
    }

    public CommentsWithGoodDTO(Long id, LocalDate date_comment, UserListsDTO usersListsId, GoodsGetAllDTO good, String comment) {
        this.id = id;
        this.date_comment = date_comment;
        this.usersListsId = usersListsId;
        this.good = good;
        this.comment = comment;
    }

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

    public UserListsDTO getUsersListsId() {
        return usersListsId;
    }

    public void setUsersListsId(UserListsDTO usersListsId) {
        this.usersListsId = usersListsId;
    }

    public GoodsGetAllDTO getGood() {
        return good;
    }

    public void setGood(GoodsGetAllDTO good) {
        this.good = good;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
