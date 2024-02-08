package com.example.board_project.Dto;


import com.example.board_project.Entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private Long articleid;
    private String nickname;
    private String content;

    public static CommentDto toDto(CommentEntity commentEntity) {
        return new CommentDto(
                commentEntity.getId(),
                commentEntity.getArticle().getId()
                ,commentEntity.getNickname()
        ,commentEntity.getContent());
    }
}
