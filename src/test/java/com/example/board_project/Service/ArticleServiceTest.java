package com.example.board_project.Service;

import com.example.board_project.Entity.ArticleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void getArticle() {
        ArticleEntity article = articleService.getArticle(624L);
        ArticleEntity test1 = new ArticleEntity(624L,"naem","efqw",2L);

        assertEquals(test1.toString(),article.toString());
    }
}