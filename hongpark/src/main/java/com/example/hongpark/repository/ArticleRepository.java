package com.example.hongpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hongpark.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	
}
