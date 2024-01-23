package sn.pts.comment.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import sn.pts.comment.web.dto.response.ValidationRspError;
import sn.pts.comment.web.tools.response.CommentMessage;
import sn.pts.comment.web.tools.response.CommentResponse;

import javax.naming.AuthenticationException;
import javax.naming.SizeLimitExceededException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

//annotation we will make it listen for exceptions thrown from all controller
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //exception par défaut
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommentResponse> exceptionHandler(Exception exception) {
//        exception.printStackTrace();
//        LOGGER.error("{}", exception.getMessage());
        return ResponseEntity.internalServerError().body(CommentResponse.error(CommentMessage.INTERNAL_SERVER_ERROR).errors(exception.getMessage()));
    }

    //pour les comment exception
    @ExceptionHandler(CommentException.class)
    public ResponseEntity<CommentResponse> handleSaytuException(CommentException exception) {
        // exception.printStackTrace();
        //LOGGER.error("{}", exception.getMessage());
        return ResponseEntity.badRequest().body(CommentResponse.error(exception).errors(exception.getMessage()));
    }

    //pour les contraintes de validations au niveau des méthodes
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CommentResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<ValidationRspError> errors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> ValidationRspError.builder().field(fieldError.getField()).message(fieldError.getDefaultMessage()).build())
                .collect(Collectors.toList());

        // LOGGER.error("{}", errors);

        return ResponseEntity.badRequest().body(CommentResponse.error(CommentMessage.CONSTRAINT_VIOLATION).errors(errors));
    }

    //pour les contraintes de validations au niveau des objets
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CommentResponse> handleViolationException(ConstraintViolationException exception) {
        List<ValidationRspError> errors = exception.getConstraintViolations()
                .stream()
                .map(fieldError -> ValidationRspError.builder().field(fieldError.getPropertyPath().toString()).message(fieldError.getMessage()).build())
                .collect(Collectors.toList());

        // LOGGER.error("constraints", errors);
        return ResponseEntity.badRequest().body(CommentResponse.error(CommentMessage.CONSTRAINT_VIOLATION).errors(errors));

    }

/*    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<CommentResponse> handleAmazonS3Exception(AmazonS3Exception ex) {
        return ResponseEntity.ok().body(new CommentResponse(CommentMessage.WS_AMAZON_ERROR, ex));
    }*/

    /**
     * SECURITY EXCEPTIONS
     **/

    //pour les accès à des ressources (points terminaux des controleurs) non autorisés
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<CommentResponse> handleAccessDeniedException(AccessDeniedException exception) {
        // exception.printStackTrace();
        // LOGGER.error("An exception occurred with message: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(CommentResponse.error(CommentMessage.UNAUTHORIZED).message("the resource you tried to reach is absolutely forbidden for some reason.").errors(exception.getMessage()));
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CommentResponse> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        // exception.printStackTrace();
        // LOGGER.error("An exception occurred with message: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CommentResponse.error(CommentMessage.NOT_FOUND).message(exception.getMessage()).errors(exception.getMessage()));
    }

    //pour les accès non autorisés
    @ExceptionHandler({AuthenticationException.class, MissingCsrfTokenException.class, InvalidCsrfTokenException.class, SessionAuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<CommentResponse> handleAuthenticationException(RuntimeException ex, HttpServletRequest request) {
        // LOGGER.error("An exception occurred with message: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpServletResponse.SC_UNAUTHORIZED)
                .body(
                        CommentResponse
                                .error(CommentMessage.UNAUTHORIZED)
                                .message("Vous n'avez pas la permission d'accéder à cette ressource !")
                                .errors(ex.getMessage())
                                .url(UrlUtils.buildFullRequestUrl(request))
                );
    }

    //pour les accès non autorisés
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<CommentResponse> handleBadCredentialsException(BadCredentialsException ex) {
        // LOGGER.error("An exception occurred with message: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommentResponse.error(CommentMessage.LOGIN_BAD_CREDENTIALS).message("Incorrect login or password!").errors(ex.getMessage()));
    }

    /* pour le traitement des fichiers */
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public ResponseEntity<CommentResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        // LOGGER.error("An exception occurred with message: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(CommentResponse.error(CommentMessage.UNAUTHORIZED).message("Vous avez dépassé la taille maximale autorisée !").errors(ex.getMessage()));
    }

    @ExceptionHandler(value = SizeLimitExceededException.class)
    public ResponseEntity<CommentResponse> handleSizeLimitExceededException(SizeLimitExceededException ex) {
        // LOGGER.error("An exception occurred with message: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(CommentResponse.error(CommentMessage.UNAUTHORIZED).message("Vous avez dépassé la taille maximale autorisée par requête !").errors(ex.getMessage()));
    }

    /* fin pour le traitement des fichiers */

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<CommentResponse> handleParseExceptionHandler(ParseException exception) {
        // LOGGER.error("An exception occurred with message: {}", exception.getMessage());
        return ResponseEntity.badRequest().body(CommentResponse.error(CommentMessage.INTERNAL_SERVER_ERROR).message("Le format de date fournit n'est pas pris en compte !").errors(exception.getMessage()));
    }


    @ExceptionHandler(LockedException.class)
    public ResponseEntity<CommentResponse> handleLockedException(LockedException exception) {
        // LOGGER.error("An exception occurred with message: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommentResponse.error(CommentMessage.ACCOUNT_DISABLED).errors(exception.getMessage()));
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<CommentResponse> handleDisabledException(DisabledException exception) {
        // LOGGER.error("An exception occurred with message: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommentResponse.error(CommentMessage.ACCOUNT_DISABLED).errors(exception.getMessage()));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<CommentResponse> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException exception) {
        // LOGGER.error("An exception occurred with message: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommentResponse.error(CommentMessage.LOGIN_MAX_ATTEMPT).errors(exception.getMessage()));
    }

/*    @ExceptionHandler(JobParametersInvalidException.class)
    public ResponseEntity<CommentResponse> handleJobParametersInvalidException(JobParametersInvalidException exception) {
        // LOGGER.error("An exception occurred with message: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommentResponse.error(CommentMessage.INVALID_FILE, exception.getMessage()).errors(exception.getMessage()));
    }*/
}
