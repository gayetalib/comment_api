package sn.pts.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("{id}")
    public ResponseEntity<?> getComment(@PathVariable("id") Long id){
        CommentEntity comment = service.getComment(id);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id, @RequestBody CommentEntity dto){
        CommentEntity comment = service.updateComment(id, dto);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
        service.deleteComment(id);
        return ResponseEntity.ok().body("Successfully deleted !");
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addComment(@RequestBody CommentEntity entity){
        CommentEntity comment = service.addComment(entity);
        return ResponseEntity.ok(comment);
    }
}
