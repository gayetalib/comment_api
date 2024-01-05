package sn.pts.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.pts.comment.entity.CommentEntity;
import sn.pts.comment.service.interfaces.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    final CommentService service;

    @GetMapping
    public ResponseEntity<?> getComments(){
        List<CommentEntity> comments = service.getComments();
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentEntity entity){
        CommentEntity comment = service.addComment(entity);
        return ResponseEntity.ok(comment);
    }
}
