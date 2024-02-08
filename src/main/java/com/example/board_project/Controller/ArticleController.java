package com.example.board_project.Controller;

import com.example.board_project.ArticleRepository.ArticleRepository;
import com.example.board_project.ArticleRepository.CommentRepository;
import com.example.board_project.Dto.CommentDto;
import com.example.board_project.Entity.ArticleEntity;
import com.example.board_project.Service.ArticleService;
import com.example.board_project.Service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;
    @GetMapping("/articles")   //전체 게시글조회
    public String index(Model model, @PageableDefault(size=10,sort="id",direction = Sort.Direction.ASC)Pageable pageable)
    {
        //페이지 구현
        Page<ArticleEntity> articles = articleService.getAllArticle(pageable);
        model.addAttribute("articles",articles);

        //페이지 이전, 이후 페이지 모델
        model.addAttribute("present",pageable.getPageNumber());
        model.addAttribute("previous",pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next",pageable.next().getPageNumber());
        model.addAttribute("nextnext",pageable.next().next().getPageNumber());
        model.addAttribute("lastpage",articleService.lastpage(articles));

        //라스트 페이지 체크(서비스에 함수 미구현)
        model.addAttribute("check",articles.hasNext());

        //라스트 페이지 체크(서비스에 함수 구현)
        //model.addAttribute("check",articleService.checklastpage(pageable));

        /* // 페이징 없이 하기
        List<ArticleEntity> articlelist = articleService.getAllArticle();
        model.addAttribute("articles",articlelist);
        */

        return "articles/index";
    }

    @GetMapping("/articles/{id}")    //게시물 조회
    public String show(@PathVariable Long id,Model model)
    {
        ArticleEntity article = articleService.getArticle(id);
        articleService.updateview(id);
        model.addAttribute("article",article);
        List<CommentDto> comments = commentService.comments(id);
        model.addAttribute("comments",comments);
        return "articles/show";
    }

    @GetMapping("/articles/new")
    public String create()
    {
        return "articles/new";
    }

    @GetMapping("/articles/edit/{id}")
    public String edit(@PathVariable Long id,Model model)
    {
        ArticleEntity article = articleService.getArticle(id);
        model.addAttribute("article",article);

        return "articles/edit";
    }

    @Transactional
    @PostMapping("/articles/search")
    public String searchcontent(String searchcontent,int searchoption) throws UnsupportedEncodingException {
        if(searchcontent.isEmpty())
        {
            return "redirect:/articles";
        }
        String oksearchcontent = URLEncoder.encode(searchcontent , StandardCharsets.UTF_8);  //url 에 한글이 입력되었을때 오류를 해결하기 위한 코드

        if(searchoption == 1)
            return "redirect:/articles/search/title/"+oksearchcontent;
        else
            return "redirect:/articles/search/content/"+oksearchcontent;
    }

    @Transactional
    @GetMapping("/articles/search/title/{searchcontent}")
    public String searchbytitle(@PathVariable String searchcontent,Model model)
    {

        List<ArticleEntity> searchlist = articleService.SearchByTitle(searchcontent);
        model.addAttribute("searcharticle",searchlist);
        log.info(searchlist.toString());
        return "articles/search";
    }
    @GetMapping("/articles/search/content/{searchcontent}")
    public String searchbycontent(@PathVariable String searchcontent,Model model)
    {

        List<ArticleEntity> searchlist = articleService.SearchByContent(searchcontent);
        model.addAttribute("searcharticle",searchlist);
        log.info(searchlist.toString());
        return "articles/search";
    }
}
