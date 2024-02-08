package com.example.board_project.Entity;

import com.example.board_project.Dto.ArticleDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @Column(columnDefinition = "integer default 0", nullable = false)
    private Long viewcnt;


    public void patch(ArticleDto dto) {
        if(dto.getTitle() !=null)
            this.title=dto.getTitle();
        if(dto.getContent() !=null)
            this.content=dto.getContent();
    }
}
