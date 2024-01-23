package sn.pts.comment.web.tools.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import sn.pts.comment.exception.CommentException;

import java.util.Objects;

@Data
@SuperBuilder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentResponse {
    private boolean success;
    private String message;
    private Object data;
    private String status;
    private Object errors;
    private String url;

    public static CommentResponse success(Object data) {
        return CommentResponse.builder().success(true).data(data).build();
    }

    public static CommentResponse error(CommentMessage CommentMessage) {
        return CommentResponse.builder()
                .success(false)
                .status(CommentMessage.getCode())
                .message(CommentMessage.getMessage())
                .build();
    }

    public static CommentResponse error(CommentException saytuException) {
        return CommentResponse.builder()
                .success(false)
                .status(saytuException.getCode())
                .message(saytuException.getMessage())
                .build();
    }

    public static CommentResponse error(CommentMessage CommentMessage, String s) {
        return CommentResponse.builder()
                .success(false)
                .status(CommentMessage.getCode())
                .message(String.format(CommentMessage.getMessage(), s))
                .build();
    }

    public CommentResponse message(String message) {
        this.message = message;
        return this;
    }

    public CommentResponse data(Object data) {
        if (Objects.isNull(this.data)) this.data = data;
        return this;
    }

    public CommentResponse errors(Object errors) {
        this.errors = errors;
        return this;
    }

    public CommentResponse url(String url) {
        this.url = url;
        return this;
    }
}

