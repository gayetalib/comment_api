package sn.pts.comment.web.dto.mapper;

import org.mapstruct.Mapper;
import sn.pts.comment.entity.CommentEntity;
import sn.pts.comment.web.dto.request.CommentReqDTO;
import sn.pts.comment.web.dto.response.CommentRspDTO;

@Mapper
public interface CommentMapper extends EntityMapper<CommentEntity, CommentReqDTO, CommentRspDTO> {
}
