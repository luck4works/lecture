package com.example.hongpark.api;

import com.example.hongpark.annotation.RunningTime;
import com.example.hongpark.dto.CommentDto;
import com.example.hongpark.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentApiController {
	
	@Autowired
	CommentService commentService;

	@GetMapping("/articles/{articleId}/comments")
	public ResponseEntity<List<CommentDto>> commentList(@PathVariable Long articleId){
		List<CommentDto> dtos = commentService.commentDtoList(articleId);

		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}

	@PostMapping("/articles/{articleId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable Long articleId,
													@RequestBody CommentDto dto) {
		CommentDto createdDto = commentService.create(articleId, dto);
		return ResponseEntity.status(HttpStatus.OK).body(createdDto);
	}

	@PatchMapping("/comments/{id}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable Long id,
													@RequestBody CommentDto dto){
		CommentDto updatedDto = commentService.update(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
	}
	
	@RunningTime
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<CommentDto> deleteComment(@PathVariable Long id){
		CommentDto deletedDto = commentService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
	}

}
