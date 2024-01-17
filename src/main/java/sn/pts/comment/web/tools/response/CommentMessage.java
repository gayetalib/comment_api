package sn.pts.comment.web.tools.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommentMessage {
   /* GENERIC_ERROR,
    GENERIC_NOT_FOUND,
    INTERNAL_SERVER_ERROR,*/
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "4001", "Comment not found %s");

    private int httpStatus;
    private String code;
    private String message;

    CommentMessage(int httpStatus, String code, String message){
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
