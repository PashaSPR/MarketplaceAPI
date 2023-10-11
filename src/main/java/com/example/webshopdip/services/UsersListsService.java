package com.example.webshopdip.services;

import com.example.webshopdip.dtos.GoodsGetAllDTO;
import com.example.webshopdip.dtos.UserListsDTO;
import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.entities.UsersListsEntity;
import com.example.webshopdip.exceptions.UsersListsNotFoundException;
import com.example.webshopdip.repositories.UsersListsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersListsService {

    @Autowired
    private UsersListsRepository usersListsRepository;
    public UsersListsEntity createUsersLists(UsersListsEntity usersListsEntity){
        return usersListsRepository.save(usersListsEntity);
    }
    public UsersListsEntity getOne(Long id) throws UsersListsNotFoundException {
        Optional<UsersListsEntity> optional = usersListsRepository.findById(id);
        if(optional.isEmpty()){
            throw new UsersListsNotFoundException("Користувача не знайдено");
        }
        return optional.get();
    }

    public UserListsDTO entityToDTO(UsersListsEntity entity) {

        UserListsDTO dto = new UserListsDTO();
        dto.setId(entity.getId());
        dto.setNickname(entity.getNickname());

        return dto;
    }
    public void deleteUsersLists(Long id) {
        usersListsRepository.deleteById(id);
    }
}