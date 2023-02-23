package com.example.hongpark.dto;

import com.example.hongpark.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDto {
    Long id;
    @JsonProperty("article_id")
    Long articleId;
    String nickname;
    String body;

    public static CommentDto createCommentDto(Comment c) {
        return new CommentDto(c.getId(), c.getArticle().getId(), c.getNickname(), c.getBody());
    }
}
