package com.example.hongpark.controller;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.dto.CommentDto;
import com.example.hongpark.entity.Article;
import com.example.hongpark.entity.Comment;
import com.example.hongpark.repository.ArticleRepository;
import com.example.hongpark.repository.CommentRepository;
import com.example.hongpark.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Controller
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CommentService commentService;

	@PostConstruct
	public void init() {

		Article a1 = new Article(null, "영화", "111");
		Article a2 = new Article(null, "음식", "222");
		Article a3 = new Article(null, "취미", "333");
		List<Article> articleList = Arrays.asList(a1, a2, a3);

		Comment c1 = new Comment(null, "김씨", "쇼생크탈출", a1);
		Comment c2 = new Comment(null, "박씨", "태극기펄럭", a1);
		Comment c3 = new Comment(null, "최씨", "치킨", a2);
		Comment c4 = new Comment(null, "박씨", "피자", a2);
		Comment c5 = new Comment(null, "최씨", "축구", a3);
		Comment c6 = new Comment(null, "김씨", "농구", a3);
		List<Comment> commentList = Arrays.asList(c1, c2, c3, c4,c5,c6);
		articleRepository.saveAllAndFlush(articleList);
		commentRepository.saveAllAndFlush(commentList);

	}
	 	
	@GetMapping("/articles/new")
	public String newArticleForm() {
		return "articles/new";
	}	
	
	@PostMapping("/articles/create")
	public String createArticle(ArticleForm form) {
		Article article = form.toEntity();
		Article saved = articleRepository.save(article);
		return "redirect:/articles/" + saved.getId();
	}
	
	@GetMapping("/articles/{id}")
	public String show(@PathVariable Long id, Model model) {
		Article articleEntity = articleRepository.findById(id).orElse(null);
		List<CommentDto> commentDtos = commentService.commentDtoList(id);
		model.addAttribute("article", articleEntity);
		model.addAttribute("commentDtos", commentDtos);
		return "articles/show";
	}
	
	@GetMapping("/articles")
	public String index(Model model) {
		List<Article> articleEntityList = articleRepository.findAll();
		model.addAttribute("articleList", articleEntityList);
		return "articles/index";
	}
	
	@GetMapping("/articles/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		Article articleEntity = articleRepository.findById(id).orElse(null);
		model.addAttribute("article", articleEntity);
		return "articles/edit";
	}
	
	@PostMapping("/articles/update")
	public String update(ArticleForm form) {
		Article articleEntity = form.toEntity();
		Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
		if(target!=null) {
			articleRepository.save(articleEntity);			
		}
		return "redirect:/articles/" + articleEntity.getId();
	}
	
	@GetMapping("/articles/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes ra) {
		Article target = articleRepository.findById(id).orElse(null);
		if(target!=null) {
			articleRepository.delete(target);			
		}
		ra.addFlashAttribute("msg", "삭제완료");
		return "redirect:/articles";
	}

}

