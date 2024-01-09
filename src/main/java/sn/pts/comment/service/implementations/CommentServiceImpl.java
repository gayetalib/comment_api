package sn.pts.comment.service.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.pts.comment.entity.CommentEntity;
import sn.pts.comment.repository.CommentRepository;
import sn.pts.comment.service.interfaces.CommentService;
import sn.pts.comment.web.dto.request.CommentReqDTO;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    @Override
    public List<CommentEntity> getComments() {
        return repository.findAll();
    }

    @Override
    public CommentEntity getComment(Long id) {
        return repository.findById(id).orElseThrow(null);
    }

    @Override
    public CommentEntity addComment(CommentReqDTO dto) {
        CommentEntity comment = CommentEntity.builder()
                .author(dto.getAuthor())
                .text(dto.getText())
                .date(Date.from(Instant.now()))
                .build();
        return repository.save(comment);
    }

    @Override
    public CommentEntity updateComment(Long id, CommentReqDTO dto) {
        CommentEntity existingComment = getComment(id);
        existingComment.setAuthor(dto.getAuthor());
        existingComment.setText(dto.getText());
        return repository.save(existingComment);
    }

    @Override
    public void deleteComment(Long id) {
         //repository.deleteById(id);
        CommentEntity comment = getComment(id);
        comment.setDeleted(true);
        repository.save(comment);
    }
}
