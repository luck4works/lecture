package com.example.hongpark.service;

import com.example.hongpark.dto.CommentDto;
import com.example.hongpark.entity.Article;
import com.example.hongpark.entity.Comment;
import com.example.hongpark.repository.ArticleRepository;
import com.example.hongpark.repository.CommentRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ArticleRepository articleRepository;

    public List<CommentDto> commentDtoList(Long articleId) {
        return commentRepository.findAllByArticleId(articleId).stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
    	//log.info("입력값 => {}", dto);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("대상 게시글이 없습니다."));
        Comment comment = Comment.createComment(dto, article);
        Comment created = commentRepository.save(comment);
        CommentDto createdDto = CommentDto.createCommentDto(created);
        //log.info("반환값 => {}", createdDto);
        return createdDto;
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("대상 댓글이 없음"));
        target.update(dto);
        Comment updated = commentRepository.save(target);
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("대상 댓글 없음"));
        commentRepository.delete(target);
        return CommentDto.createCommentDto(target);
    }
}
