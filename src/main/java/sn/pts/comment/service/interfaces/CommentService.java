package sn.pts.comment.service.interfaces;

import sn.pts.comment.entity.CommentEntity;
import sn.pts.comment.web.dto.request.CommentReqDTO;

import java.util.List;

public interface CommentService {
    List<CommentEntity> getComments();
    CommentEntity getComment(Long id);
    CommentEntity addComment(CommentReqDTO dto);
    CommentEntity updateComment(Long id, CommentReqDTO dto);
    void deleteComment(Long id);
}
