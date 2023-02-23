package com.example.hongpark.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.entity.Article;

@SpringBootTest
public class ArticleServiceTest {
	@Autowired
	ArticleService articleService;
	@Test
	void index() {
		Article a = new Article(1l, "가가", "111");
		List<Article> articles = articleService.index();
		assertEquals(a.toString(), articles.get(0).toString());
	}
	@Test
	void show_성공_존재하는ID입력() {
		Long id = 1l;
		Article expected = new Article(id, "가가", "111");
		Article actual = articleService.index(id);
		assertEquals(expected.toString(), actual.toString());
	}
	@Test
	@Transactional
	void create_성공() {
		String title = "sfsdf";
		String content = "asdfdsf";
		
		ArticleForm dto = new ArticleForm(null, title, content);		
		Article expected = new Article(4l, title, content);
		
		Article actual = articleService.create(dto);
		
		assertEquals(expected.toString(), actual.toString());
	}
	@Test
	@Transactional
	void create_실패() {
		String title = "sfsdf";
		String content = "asdfdsf";
		
		ArticleForm dto = new ArticleForm(1l, title, content);		
		Article expected = null;
		
		Article actual = articleService.create(dto);
		
		assertEquals(expected, actual);
	}
}
