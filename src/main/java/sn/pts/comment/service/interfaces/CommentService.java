package sn.pts.comment.service.interfaces;

import sn.pts.comment.entity.CommentEntity;

import java.util.List;

public interface CommentService {
    List<CommentEntity> getComments();
    CommentEntity getComment(Long id);
    CommentEntity addComment(CommentEntity comment);
    CommentEntity updateComment(Long id, CommentEntity comment);
    void deleteComment(Long id);
}
