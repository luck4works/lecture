package com.example.hongpark.dto;

import com.example.hongpark.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArticleForm {
	
	private Long id;
	private String title;
	private String content;
	
	public Article toEntity() {		
		return new Article(id, title, content);
	}

}
