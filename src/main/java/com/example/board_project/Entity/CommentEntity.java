package com.example.board_project.Entity;

import com.example.board_project.Dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="article_id")
    private ArticleEntity article;
    @Column
    private String nickname;
    @Column
    private String content;

    public static CommentEntity toEntity(CommentDto comment, ArticleEntity article) throws IllegalAccessException {
        if(comment.getId() !=null)
            throw new IllegalAccessException("id 입력 금지");

        return new CommentEntity(comment.getId(),article,comment.getNickname(),comment.getContent());
    }

    public void patch(CommentDto comment) {
        if(comment.getNickname() != null)
            this.nickname = comment.getNickname();
        if(comment.getContent() != null)
            this.content = comment.getContent();
    }
}
