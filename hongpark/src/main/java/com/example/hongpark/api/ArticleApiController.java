package com.example.hongpark.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hongpark.dto.ArticleForm;
import com.example.hongpark.entity.Article;
import com.example.hongpark.service.ArticleService;

@RestController
@RequestMapping("/api")
public class ArticleApiController {
	
	@Autowired
	ArticleService articleSerivce;
	
	@GetMapping("/articles")
	public List<Article> index() {
		return articleSerivce.index();
	}
	
	@GetMapping("/articles/{id}")
	public Article index(@PathVariable Long id) {
		return articleSerivce.index(id);
	}
	
	@PostMapping("/articles")
	public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
		Article created = articleSerivce.create(dto);
		return (created != null) ? 
				ResponseEntity.status(HttpStatus.OK).body(created) :
					ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.build();
	}
	
	@PatchMapping("/articles/{id}")
	public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {		
		Article updated = articleSerivce.update(id, dto);
		return (updated != null) ? 
				ResponseEntity.status(HttpStatus.OK).body(updated) :
					ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@DeleteMapping("/articles/{id}")
	public ResponseEntity<Article> delete(@PathVariable Long id) {
		Article deleted = articleSerivce.delete(id);	
		return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).body(deleted) :
			ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			
	}
	
	@PostMapping("/transaction-test")
	public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
		List<Article> createdList = articleSerivce.createArticles(dtos);
		return (createdList!=null) ?
				ResponseEntity.status(HttpStatus.OK).body(createdList) :
					ResponseEntity.status(HttpStatus.BAD_REQUEST).build();		
	}
}
