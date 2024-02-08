package com.example.board_project.ApiController;

import com.example.board_project.ArticleRepository.CommentRepository;
import com.example.board_project.Dto.CommentDto;
import com.example.board_project.Entity.CommentEntity;
import com.example.board_project.Service.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;



    @GetMapping("/api/comments/{articleid}")
    public ResponseEntity<List<CommentDto>> commentindex(@PathVariable Long articleid)
    {
            List<CommentDto> list = commentService.comments(articleid);

            return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @PostMapping("/api/articles/{articleid}/comments")
    public ResponseEntity<CommentDto> createcomment(@PathVariable Long articleid,@RequestBody CommentDto comment) throws IllegalAccessException {
        CommentDto created = commentService.createcomment(articleid,comment);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> updatecomment(@PathVariable Long id,@RequestBody CommentDto comment)
    {
            CommentDto result = commentService.update(id,comment);
            return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> deletecomment(@PathVariable Long id)
    {
            CommentDto deleted = commentService.delete(id);

            return ResponseEntity.status(HttpStatus.OK).body(deleted);

    }

}
