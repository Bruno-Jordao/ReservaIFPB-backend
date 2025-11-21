package com.example.ReservaIFPB_backend.web.exception;

import com.example.ReservaIFPB_backend.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> MethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result){
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Invalid field(s)", result));
    }


    @ExceptionHandler({
            CampusNotFoundException.class,
            CampusAlreadyExistsException.class,
            BlockNotFoundException.class,
            BlockAlreadyExistsException.class,
            RoomNotFoundException.class,
            RoomAlreadyExistsException.class
    })
    public ResponseEntity<ErrorMessage> handleDomainExceptions(RuntimeException ex,
                                                               HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (ex instanceof CampusNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }

        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, status, ex.getMessage()));
    }
}
