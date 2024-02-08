package com.example.board_project.Dto;


import com.example.board_project.Entity.ArticleEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;

@AllArgsConstructor
@ToString
@Getter
public class ArticleDto {

    private Long id;
    private String title;
    private String content;
    private Long viewcnt;

    public ArticleEntity toEntity() {
       return new ArticleEntity(null,title,content,viewcnt);
    }
}
