package com.example.hongpark.entity;

import com.example.hongpark.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nickname;
	
	@Column
	private String body;

	@ManyToOne
	private Article article;

	public static Comment createComment(CommentDto dto, Article article) {
		if(dto.getId() != null) throw new IllegalArgumentException("댓글의 ID가 있으면 안됨");
		if(!dto.getArticleId().equals(article.getId())) throw new IllegalArgumentException("게시글의 ID가 잘못됨");
		return new Comment(null, dto.getNickname(), dto.getBody(), article);
	}

	public void update(CommentDto dto) {
		if(!this.id.equals(dto.getId()))  throw new IllegalArgumentException("ID가 잘못됨");
		if(dto.getNickname()!=null) this.nickname = dto.getNickname();
		if(dto.getBody()!=null) this.body = dto.getBody();
	}
}
