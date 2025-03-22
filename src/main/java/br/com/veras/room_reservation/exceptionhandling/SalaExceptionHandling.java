package br.com.veras.room_reservation.exceptionhandling;

import br.com.veras.room_reservation.exception.ErrorDetail;
import br.com.veras.room_reservation.exception.NotFoundSalaException;
import br.com.veras.room_reservation.exception.RoomReservedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SalaExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundSalaException.class)
    public ResponseEntity<ErrorDetail> notFoundSalaException(NotFoundSalaException sala){
        ErrorDetail error = new ErrorDetail(HttpStatus.NOT_FOUND,"Está Sala não existe!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(RoomReservedException.class)
    public ResponseEntity<ErrorDetail> roomReservedException(RoomReservedException reservado){
        ErrorDetail error = new ErrorDetail(HttpStatus.NOT_FOUND,"Está sala já está reservada!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
