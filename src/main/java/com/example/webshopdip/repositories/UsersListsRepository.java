package com.example.webshopdip.repositories;

import com.example.webshopdip.entities.UsersListsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersListsRepository extends JpaRepository<UsersListsEntity, Long> {
    //    List<UsersListsEntity> findByUserListId(Long user_id);

}