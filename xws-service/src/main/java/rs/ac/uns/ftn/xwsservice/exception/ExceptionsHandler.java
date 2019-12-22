package rs.ac.uns.ftn.xwsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {
            ApiRequestException.class,
            OperationFailedException.class
    })
    public ResponseEntity<Object> handleApiRequestException(Exception e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorMessage apiException = new ErrorMessage(e.getMessage(), badRequest, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(Exception e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ErrorMessage apiException = new ErrorMessage(e.getMessage(), notFound, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, notFound);
    }
}
