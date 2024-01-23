package sn.pts.comment.web.tools.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static sn.pts.comment.commons.constant.AppConstants.ATTEMPT_CACHE_DURATION_TIME;

@Getter
public enum CommentMessage {

    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "4001", "Comment not found %s"),

    GENERIC_ERROR(HttpStatus.BAD_REQUEST.value(), "400014", "%s"),
    GENERIC_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "404000", "There is no %s"),

    // -- internal server error messages
    UNKNOWN_ERROR(HttpStatus.SERVICE_UNAVAILABLE.value(), "500000",
            "An internal problem has occurred, please contact Petrosen support %s"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "500001",
            "Internal Server Error, please contact customer service %s"),
    WS_AMAZON_ERROR(HttpStatus.BAD_REQUEST.value(), "500002",
            "Unknown error occurred, please contact customer service %s"),
    // -- end internal server error messages

    // -- not found messages
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "404100", "Not found %s"),
    STATUS_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "404101", "Status not found %s"),
    CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST.value(), "400003", "Not valid due to validation error %s"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "401003", "is not allowed%s"),
    LOGIN_BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED.value(), "401004", "Incorrect login or password %s"),
    ACCOUNT_DISABLED(HttpStatus.UNAUTHORIZED.value(), "401020", "User account is disabled %s"),
    OLD_PASSWORD_INVALID(HttpStatus.UNAUTHORIZED.value(), "401021", "Old password is invalid %s"),
    NEW_AND_CONFIRM_PASSWORD_MUST_MATCH(HttpStatus.UNAUTHORIZED.value(), "401012",
            "New and confirm password must match"),
    SITE_DISABLED(HttpStatus.UNAUTHORIZED.value(), "401022", "Site is disabled %s"),
    LINE_DISABLED(HttpStatus.UNAUTHORIZED.value(), "401023", "Line is disabled %s"),
    LOGIN_MAX_ATTEMPT(HttpStatus.BAD_REQUEST.value(), "400002",
            "You have exceeded the number of attempts allowed for the connection. Please try again in %s"
                    + ATTEMPT_CACHE_DURATION_TIME + " minutes."),
    // already exist messages
    ACCOUNT_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), "400004", "Account already exist : %s"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "401007",
            "The token provided is not valid for the following reason: %s"),
    INVALID_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED.value(), "401013",
            "The token provided is not valid for the following reason: JWT expired %s"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "404104", "No user were found %s"),
    ;

    private int httpStatus;
    private String code;
    private String message;

    CommentMessage(int httpStatus, String code, String message){
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
