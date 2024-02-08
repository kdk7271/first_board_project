package com.example.board_project.Service;

import com.example.board_project.ArticleRepository.ArticleRepository;
import com.example.board_project.ArticleRepository.CommentRepository;
import com.example.board_project.Dto.CommentDto;
import com.example.board_project.Entity.ArticleEntity;
import com.example.board_project.Entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleid) {
        return commentRepository.findByArticleId(articleid).stream().
                map(commentEntity->CommentDto.toDto(commentEntity)).collect(Collectors.toList());
    }

    @Transactional
    public CommentDto createcomment(Long articleid,CommentDto comment) throws IllegalAccessException {
        ArticleEntity article = articleRepository.findById(articleid).orElseThrow(()->new IllegalArgumentException());

        CommentEntity targetcomment = CommentEntity.toEntity(comment,article);
        CommentEntity created = commentRepository.save(targetcomment);
        return CommentDto.toDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto comment) {

        CommentEntity target = commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        target.patch(comment);
        commentRepository.save(target);
        return CommentDto.toDto(target);

    }

    public CommentDto delete(Long id) {
        CommentEntity target = commentRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        commentRepository.delete(target);

        return CommentDto.toDto(target);
    }
}
