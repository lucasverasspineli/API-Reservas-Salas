package br.com.veras.room_reservation.exceptionhandling;

import br.com.veras.room_reservation.exception.ErrorDetail;
import br.com.veras.room_reservation.exception.NotFoundSalaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SalaExceptionHandling {

    @ExceptionHandler(NotFoundSalaException.class)
    public ResponseEntity<ErrorDetail> notFoundSalaException(NotFoundSalaException sala){
        ErrorDetail error = new ErrorDetail(HttpStatus.NOT_FOUND,"Está Sala não existe!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
