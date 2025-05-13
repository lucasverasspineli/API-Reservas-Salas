package br.com.veras.room_reservation.exceptionhandling;

import br.com.veras.room_reservation.exception.ErrorDetail;
import br.com.veras.room_reservation.exception.ReservationNotExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ReservationExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReservationNotExist.class)
    public ResponseEntity<ErrorDetail> ReservationNotExistException(ReservationNotExist reservation){
        ErrorDetail erro = new ErrorDetail(HttpStatus.NOT_FOUND,"Essa reserva não existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }



}
