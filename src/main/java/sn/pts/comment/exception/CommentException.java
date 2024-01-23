package sn.pts.comment.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import sn.pts.comment.web.tools.response.CommentMessage;

@Data
public class CommentException extends Exception {

    protected String code;

    protected String message;

    protected HttpStatus status;

    protected Object args;

    public CommentException(CommentMessage CommentMessage) {
        this.code = CommentMessage.getCode();
        this.status = HttpStatus.resolve(CommentMessage.getHttpStatus());
        this.message = CommentMessage.getMessage();
    }

    public CommentException(CommentMessage CommentMessage, String message) {
        this.code = CommentMessage.getCode();
        this.status = HttpStatus.resolve(CommentMessage.getHttpStatus());
        this.message = String.format(CommentMessage.getMessage(), message);
    }

    public CommentException(String code, String message, HttpStatus status) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public CommentException(CommentMessage CommentMessage, Object args) {
        this.code = CommentMessage.getCode();
        this.status = HttpStatus.resolve(CommentMessage.getHttpStatus());
        this.message = CommentMessage.getMessage();
        this.args = args;
    }

    @Override
    public String toString() {
        return String.format("CommentException: %s - %s", code, message);
    }
}
