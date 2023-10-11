package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Користувачі".
 * Містить дані та поведінку, пов'язані з Користувачами магазину.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "userslists")
public class UsersListsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Користувача
    private String nickname; // Унікальне ім'я Користувача
    private String password;// пароль Користувача (String??? чи інший тип)
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "rolesUsers_id", referencedColumnName = "id")
    // Зв'язок Many-to-One: Багато Користувачі можуть мати одну Роль
    private RolesUsersEntity rolesUsers;
    /////////сутності що мають відношення One-to-Many з сутністю UsersLists
    @OneToMany(mappedBy = "usersLists")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один Користувач може мати багато Питань(відповідей) щодо певного Товару
    private List<QuestionsEntity> questions = new ArrayList<>();
    @OneToMany(mappedBy = "usersLists")
    @JsonManagedReference
    // Зв'язок One-to-Many: Один Користувач може мати багато Коментарів щодо певного Товару
    private List<CommentsEntity> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolesUsersEntity getRolesUsers() {
        return rolesUsers;
    }

    public void setRolesUsers(RolesUsersEntity rolesUsers) {
        this.rolesUsers = rolesUsers;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<QuestionsEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsEntity> questions) {
        this.questions = questions;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }
}
