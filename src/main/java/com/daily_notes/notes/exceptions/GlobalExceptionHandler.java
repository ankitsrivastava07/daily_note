package com.daily_notes.notes.exceptions;

import com.daily_notes.notes.dto.ErrorResponse;
import com.daily_notes.notes.records.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.daily_notes.notes.exceptions.exception.NoteNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<?> handleNoteNotFoundException(NoteNotFoundException exp) {
        logger.info("Exception Occurred {}", exp.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<?> handleNoteException(MissingRequestHeaderException exp) {
        return new ResponseEntity<>(new ApiResponse().message(exp.getLocalizedMessage()).success(Boolean.FALSE),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInternalServerException(Exception exp) {
        logger.error("Unexpected error occurred", exp);
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exp.getLocalizedMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.name());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
