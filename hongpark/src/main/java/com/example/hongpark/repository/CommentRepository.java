package com.example.hongpark.repository;

import com.example.hongpark.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	@Query(value = "select c from Comment c where c.article.id = ?1")
    List<Comment> findAllByArticleId(Long articleId);
    List<Comment> findAllByNickname(String nickname);
}
