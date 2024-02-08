package com.example.board_project;

import com.example.board_project.Dto.ArticleDto;
import com.example.board_project.Entity.ArticleEntity;
import com.example.board_project.Service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardProjectApplicationTests {

	@Autowired
	private ArticleService articleService;
	@Test


	void testJpa() {
		for (int i = 1; i <= 300; i++) {
			String title = "예시 타이틀 "+ i;
			String content = "예시 컨텐츠 "+(300-i);
			Long viewcnt=0L;
			articleService.createArticle(new ArticleDto(null,title,content,viewcnt));
		}
	}
}
