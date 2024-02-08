package com.example.board_project.ArticleRepository;

import com.example.board_project.Entity.ArticleEntity;
import com.example.board_project.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {

    @Query(value = "SELECT * FROM comment_entity WHERE article_id = :articleId" , nativeQuery = true)
    List<CommentEntity> findByArticleId(Long articleId);
}
