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
            XMLDataParseException.class,
            XMLSchemaParseException.class
    })
    public ResponseEntity<Object> handleApiRequestException(Exception e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ErrorMessage apiException = new ErrorMessage(e.getMessage(), badRequest, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, badRequest);
    }
}
