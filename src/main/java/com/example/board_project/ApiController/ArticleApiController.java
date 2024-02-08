package com.example.board_project.ApiController;

import com.example.board_project.Dto.ArticleDto;
import com.example.board_project.Entity.ArticleEntity;
import com.example.board_project.Service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/api/articles")
    public Page<ArticleEntity> index(Pageable pageable)
    {
        return articleService.getAllArticle(pageable);
    }

    @GetMapping("/api/articles/{id}")
    public ArticleEntity show(@PathVariable Long id)
    {
        return articleService.getArticle(id);
    }

    @Transactional
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleEntity> create(@RequestBody ArticleDto articleDto){
        ArticleEntity saved = articleService.createArticle(articleDto);

            return (saved !=null) ? ResponseEntity.status(HttpStatus.OK).body(saved)
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @Transactional
    @PatchMapping("api/articles/{id}")
    public ResponseEntity<ArticleEntity> update(@PathVariable Long id, @RequestBody ArticleDto articledto)
    {
        ArticleEntity updated= articleService.update(id,articledto);
        return (updated !=null) ? ResponseEntity.status(HttpStatus.OK).body(updated)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @Transactional
    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<ArticleEntity> delete(@PathVariable Long id)
    {
        ArticleEntity Deleted = articleService.delete(id);

        return (Deleted != null) ? ResponseEntity.
                status(HttpStatus.OK).body(Deleted) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
