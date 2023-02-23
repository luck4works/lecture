package com.example.hongpark.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.entity.Article;
import com.example.hongpark.repository.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	ArticleRepository articleRepository;
	public List<Article> index() {
		return articleRepository.findAll();
	}
	public Article index(@PathVariable Long id) {
		return articleRepository.findById(id).orElse(null);
	}
	public Article create(@RequestBody ArticleForm dto) {
		Article article = dto.toEntity();
		if(article.getId()!=null&&articleRepository.existsById(article.getId()))
			return null;
		return articleRepository.save(article);
	}
	public Article update(@PathVariable Long id, @RequestBody ArticleForm dto) {
		Article article = dto.toEntity();
		Article target = articleRepository.findById(id).orElse(null);
		if(target==null || id!=article.getId()) {
			return null;
		}
		target.patch(article);
		Article updated = articleRepository.save(target); 
		return updated;
	}
	public Article delete(@PathVariable Long id) {
		Article target = articleRepository.findById(id).orElse(null);
		if(target==null) {
			return null;
		}
		articleRepository.delete(target); 
		return target;
	}
	@Transactional
	public List<Article> createArticles(List<ArticleForm> dtos){
		List<Article> articleList = dtos.stream().map(dto -> dto.toEntity())
				.collect(Collectors.toList());
		articleList.stream().forEach(article -> articleRepository.save(article));
		
		articleRepository.findById(-1L).orElseThrow(
				() -> new IllegalArgumentException("결재 실패"));
		
		return articleList;
	}
}