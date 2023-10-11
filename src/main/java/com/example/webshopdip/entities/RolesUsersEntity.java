package com.example.webshopdip.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє сутність "Ролі Користувача".
 * Містить Ролі, що надаються Користувачам.
 * Дата створення: 04.06.2023
 */
@Entity
@Table(name = "rolesusers")
public class RolesUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Унікальний ідентифікатор Ролі Користувача
    private String name; // Назва Ролі Користувача
    @OneToMany(mappedBy = "rolesUsers", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    // Зв'язок One-to-Many: Одна Роль Користувача може відноситися до багатьох Користувачів
    private List<UsersListsEntity> usersLists = new ArrayList<>();
}
