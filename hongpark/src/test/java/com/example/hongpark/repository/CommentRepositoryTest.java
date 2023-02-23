package com.example.hongpark.repository;

import com.example.hongpark.entity.Article;
import com.example.hongpark.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findAllByArticleId() {

        Long articleId = 2l;
        List<Comment> actual = commentRepository.findAllByArticleId(articleId);

        Article a2 = new Article(2l, "음식", "222");
        Comment c3 = new Comment(3l, "최씨", "치킨", a2);
        Comment c4 = new Comment(4l, "박씨", "피자", a2);
        List<Comment> expected = Arrays.asList(c3, c4);
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    @DisplayName("특정 사용자의 모든 댓글 조회")
    void findAllByByNickname() {
        String nickname = "박씨";
        List<Comment> actual = commentRepository.findAllByNickname(nickname);

        Article a1 = new Article(1l, "영화", "111");
        Article a2 = new Article(2l, "음식", "222");

        Comment c2 = new Comment(2l, "박씨", "태극기펄럭", a1);
        Comment c4 = new Comment(4l, "박씨", "피자", a2);

        List<Comment> expected = Arrays.asList(c2, c4);
        assertEquals(expected.toString(), actual.toString());
    }
}