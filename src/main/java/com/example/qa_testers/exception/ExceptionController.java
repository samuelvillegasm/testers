package com.example.qa_testers.exception;

import com.example.qa_testers.dto.response.MessageDTO;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        MessageDTO exceptionDTO = new MessageDTO(ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MessageDTO> handleValidationExceptions(HttpMessageNotReadableException ex) {
        MessageDTO exceptionDTO = new MessageDTO(ex.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageDTO> notFoundException(NotFoundException e) {
        MessageDTO exceptionDTO = new MessageDTO(e.getMessage());
        return new ResponseEntity<MessageDTO>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessageDTO> badRequestException(BadRequestException e) {
        MessageDTO exceptionDTO = new MessageDTO(e.getMessage());
        return new ResponseEntity<MessageDTO>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Object> handleMismatchedInputException(HandlerMethodValidationException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(new MessageDTO(errorMessage), HttpStatus.BAD_REQUEST);
    }
}