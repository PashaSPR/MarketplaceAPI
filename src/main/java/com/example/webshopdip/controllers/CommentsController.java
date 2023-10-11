package com.example.webshopdip.controllers;

import com.example.webshopdip.dtos.CommentsDTO;
import com.example.webshopdip.dtos.CommentsWithGoodDTO;
import com.example.webshopdip.entities.CommentsEntity;
import com.example.webshopdip.exceptions.CommentsNotFoundException;
import com.example.webshopdip.exceptions.GoodsNotFoundException;
import com.example.webshopdip.repositories.CommentsRepository;
import com.example.webshopdip.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/comments")

public class CommentsController {

    @Autowired
    private CommentsService commentsService;
    @Autowired
    private CommentsRepository commentsRepository;
    @GetMapping
    public String showComments(Model model) {
        List<CommentsEntity> comments = commentsService.getAllComments();
        model.addAttribute("comments", comments);
        return "comments/list"; // Передача імені шаблону для відображення списку відгуків
    }



    @PostMapping
    public ResponseEntity createComment(@RequestBody CommentsDTO commentsDTO) {
        System.out.println("Comments frontend " + commentsDTO);
        try {
            CommentsEntity comment = commentsService.createCommentFromDTO(commentsDTO);
            return ResponseEntity.ok("comment");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @GetMapping("/goods/{id}")
//    public ResponseEntity<List<CommentsEntity>> getCommentsByGoodsId(@PathVariable("id") Long goodsId) {
//
//        List<CommentsEntity> comments = commentsService.getCommentsByGoodsId(goodsId);
//        return new ResponseEntity<>(comments, HttpStatus.OK);
//    }

//    @GetMapping("/goods/{goodsId}")
//    public ResponseEntity<List<CommentsEntity>> getCommentsByGoodsId(@PathVariable("goodsId") Long goodsId) {
//        List<CommentsEntity> comments = commentsRepository.findByGoodsId(goodsId);
//        return new ResponseEntity<>(comments, HttpStatus.OK);
//    }

    @GetMapping("/goods/{goodsId}")
    public ResponseEntity<List<CommentsWithGoodDTO>> getCommentsByGoodsId(@PathVariable("goodsId") Long goodsId) throws GoodsNotFoundException {
        List<CommentsWithGoodDTO> comments = commentsService.getCommentsByGoodsId(goodsId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/getOne")
    public ResponseEntity getOneComments(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(commentsService.getOne(id));
        } catch (CommentsNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Виникла помилка");
        }
    }

}



