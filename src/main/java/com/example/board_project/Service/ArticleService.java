package com.example.board_project.Service;

import com.example.board_project.ArticleRepository.ArticleRepository;
import com.example.board_project.Dto.ArticleDto;
import com.example.board_project.Entity.ArticleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

        public Page<ArticleEntity> getAllArticle(Pageable pageable)
    {
        return articleRepository.findAll(pageable);
    }

    public ArticleEntity getArticle(Long id) {
        return articleRepository.findById(id).orElse(null);

    }
    @Transactional

    public ArticleEntity createArticle(ArticleDto article) {
        if(article.getId() != null) {
            log.info("post doesn't need id");
            return null;
        }
        ArticleEntity saved = article.toEntity();

        return articleRepository.save(saved);
    }

    @Transactional
    public ArticleEntity update(Long id, ArticleDto articledto) {
        ArticleEntity target = articleRepository.findById(id).orElse(null);
        if(target == null || !id.equals(articledto.getId())) {
            log.info(" id does not exists or incorrect id ");
            return null;
        }
        target.patch(articledto);
        return articleRepository.save(target);
    }

    @Transactional
    public ArticleEntity delete(Long id) {
        ArticleEntity target = articleRepository.findById(id).orElse(null);
        if(target == null)
            return null;

        articleRepository.delete(target);

        return target;

    }

    public List<ArticleEntity> SearchByTitle(String  searchcontent) {
            if( searchcontent == null)
                return null;

            return articleRepository.findByTitle(searchcontent);
    }

    public List<ArticleEntity> SearchByContent(String searchcontent) {
        if(searchcontent == null)
            return null;

        return articleRepository.findByContent(searchcontent);
    }


    //마지막 페이지인지 확인
    public Boolean checklastpage(Pageable pageable)
    {
        Page<ArticleEntity> articles = getAllArticle(pageable);
        Boolean check =articles.hasNext();
        return check;
    }


    //조회수 올리기
    @Transactional
    public int updateview(Long articleid)
    {
        return articleRepository.updateview(articleid);
    }


    //마지막 페이지 찾기
    public Long lastpage(Page<ArticleEntity> articles)
    {
        Long count= articles.getTotalElements();
        return count/10;

    }

}
