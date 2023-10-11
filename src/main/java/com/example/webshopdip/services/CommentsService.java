package com.example.webshopdip.services;

import com.example.webshopdip.dtos.CommentsDTO;
import com.example.webshopdip.dtos.CommentsWithGoodDTO;
import com.example.webshopdip.dtos.GoodsGetAllDTO;
import com.example.webshopdip.entities.CommentsEntity;
import com.example.webshopdip.entities.GoodsEntity;
import com.example.webshopdip.entities.SubcategoriesGoodsEntity;
import com.example.webshopdip.exceptions.CommentsNotFoundException;
import com.example.webshopdip.exceptions.GoodsNotFoundException;
import com.example.webshopdip.exceptions.SubcategoriesGoodsNotFoundException;
import com.example.webshopdip.exceptions.UsersListsNotFoundException;
import com.example.webshopdip.repositories.CommentsRepository;
import com.example.webshopdip.repositories.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private UsersListsService usersListsService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    public List<CommentsEntity> getAllComments() {
        return commentsRepository.findAll();
    }


    public CommentsEntity createCommentFromDTO(CommentsDTO commentsDTO) throws UsersListsNotFoundException, GoodsNotFoundException {
        CommentsEntity comment = new CommentsEntity();
        comment.setDate_comment(LocalDate.now());
        comment.setUsersLists(usersListsService.getOne(commentsDTO.getUsersListsId()));
        System.out.println("UserListsId " + commentsDTO.getUsersListsId());
        comment.setGoods(goodsService.getGoodsById(commentsDTO.getGoodsId()) );
        System.out.println("GoodsId " + commentsDTO.getGoodsId());
        comment.setComment(commentsDTO.getComment());

        return commentsRepository.save(comment);
    }



//    public List<CommentsEntity> getCommentsByGoodsId(Long goodsId) {
//        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);
//        return comments;
//    }

    public List<CommentsWithGoodDTO> getCommentsByGoodsId(Long goodsId) throws GoodsNotFoundException {
        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);

        List<CommentsWithGoodDTO> commentsDTOS = new ArrayList<>();
        for (CommentsEntity comment: comments){
//            System.out.println("comment.id: " + comment.getId());
            CommentsWithGoodDTO commentsDTO =new CommentsWithGoodDTO();
            commentsDTO.setId(comment.getId());
            commentsDTO.setComment(comment.getComment());
            commentsDTO.setDate_comment(comment.getDate_comment());
            commentsDTO.setGood(goodsService.entityToDTO(goodsService.getGoodsById(goodsId)));
            commentsDTO.setUsersListsId(usersListsService.entityToDTO(comment.getUsersLists()));
            commentsDTOS.add(commentsDTO);
        }
        return commentsDTOS;
    }

    public CommentsEntity getOne(Long id) throws CommentsNotFoundException {
        Optional<CommentsEntity> optional = commentsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CommentsNotFoundException("Коментаря не знайдено");
        }
        return optional.get();
    }
}
