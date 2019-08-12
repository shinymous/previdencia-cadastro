package com.previdencia.cadastro.controller;

import com.previdencia.cadastro.dto.ErrorDTO;
import com.previdencia.cadastro.exception.UsuarioValidacaoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
@Slf4j
public class ApiHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleGeralException(Exception ex, WebRequest request){
        ErrorDTO errorDetails = this.buildErrorDetails(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleDataIntegrityViolationException(Exception ex, WebRequest request){
        ErrorDTO errorDetails = null;
        if(ex.getCause().getCause().getMessage().contains("Duplicate entry")){
            log.error(ex.getMessage(), ex);
            errorDetails = new ErrorDTO(LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    "Cpf já cadastrado",
                    request.getContextPath());
        }else{
            errorDetails = this.buildErrorDetails(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsuarioValidacaoException.class)
    public final ResponseEntity<Object> handleUsuarioValidacaoException(Exception ex, WebRequest request){
        ErrorDTO errorDetails = this.buildErrorDetails(ex, request, HttpStatus.BAD_REQUEST);
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    private ErrorDTO buildErrorDetails(Exception ex, WebRequest request, HttpStatus httpStatus) {
        log.error(ex.getMessage(), ex);
        return new ErrorDTO(LocalDateTime.now(),
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                ex.getMessage(),
                request.getContextPath());
    }
}
