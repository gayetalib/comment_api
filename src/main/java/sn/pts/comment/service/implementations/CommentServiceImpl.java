package sn.pts.comment.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.pts.comment.entity.CommentEntity;
import sn.pts.comment.repository.CommentRepository;
import sn.pts.comment.service.interfaces.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    final CommentRepository repository;

    @Override
    public List<CommentEntity> getComments() {
        return repository.findAll();
    }

    @Override
    public CommentEntity getComment(Long id) {
        return repository.findById(id).orElseThrow(null);
    }

    @Override
    public CommentEntity addComment(CommentEntity comment) {
        return repository.save(comment);
    }

    @Override
    public CommentEntity updateComment(Long id, CommentEntity dto) {
        CommentEntity existingComment = getComment(id);
        existingComment.setAuthor(dto.getAuthor());
        existingComment.setText(dto.getText());
        return repository.save(existingComment);
    }

    @Override
    public void deleteComment(Long id) {
         repository.deleteById(id);
    }
}
