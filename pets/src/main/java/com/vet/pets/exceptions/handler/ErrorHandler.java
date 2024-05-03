package com.vet.pets.exceptions.handler;

import com.vet.pets.exceptions.PasswordNotMatches;
import com.vet.pets.exceptions.UsernameNotFound;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UsernameNotFound.class)
    public ResponseEntity<StandardError> usernameNotFound(UsernameNotFound e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError err = buildError(status, e.getLocalizedMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PasswordNotMatches.class)
    public ResponseEntity<StandardError> passwordNotMatch(PasswordNotMatches e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        StandardError err = buildError(status,  e.getLocalizedMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    private StandardError buildError(HttpStatus status,  String exceptionMessage, String requestUri) {
        StandardError err = new StandardError();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(status.value());
        err.setMessage(exceptionMessage);
        err.setPath(requestUri);

        return err;
    }
}
