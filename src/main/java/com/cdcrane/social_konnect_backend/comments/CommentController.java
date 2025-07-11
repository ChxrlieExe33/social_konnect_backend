package com.cdcrane.social_konnect_backend.comments;

import com.cdcrane.social_konnect_backend.comments.dto.AddCommentDTO;
import com.cdcrane.social_konnect_backend.comments.dto.CommentDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentUseCase commentUseCase;

    @Autowired
    public CommentController(CommentUseCase commentUseCase) {
        this.commentUseCase = commentUseCase;
    }

    @PostMapping
    public ResponseEntity<CommentDTO> addCommentToPostByPostId(@Valid @RequestBody AddCommentDTO addCommentDTO){

        Comment comment = commentUseCase.addCommentToPostByPostId(addCommentDTO, addCommentDTO.postId());

        return ResponseEntity.ok(new CommentDTO(comment.getId(), comment.getContent(), comment.getCreatedAt()));

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID commentId){

        commentUseCase.deleteComment(commentId);

        return ResponseEntity.status(204).build();
    }
}
