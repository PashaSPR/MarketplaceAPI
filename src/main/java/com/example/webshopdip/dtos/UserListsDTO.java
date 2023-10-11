package com.example.webshopdip.dtos;

public class UserListsDTO {
    private Long id; // Унікальний ідентифікатор Користувача
    private String nickname; // Унікальне ім'я Користувача

    public UserListsDTO() {
    }

    public UserListsDTO(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
