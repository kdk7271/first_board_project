package com.example.board_project.ArticleRepository;

import com.example.board_project.Entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<ArticleEntity,Long> {

    @Query(value = "SELECT * FROM article_entity WHERE title regexp :title ", nativeQuery = true)
    List<ArticleEntity> findByTitle(String title);


    @Query(value = "SELECT * FROM article_entity WHERE content regexp :content ", nativeQuery = true)
    List<ArticleEntity> findByContent(String content);

    @Modifying
    @Query(value="UPDATE article_entity SET viewcnt = viewcnt + 1 WHERE id = :id",nativeQuery = true)
    int updateview(Long id);  // 조회수 추가 쿼리

}
