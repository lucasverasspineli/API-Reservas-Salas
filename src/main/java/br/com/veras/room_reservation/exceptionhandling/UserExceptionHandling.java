package br.com.veras.room_reservation.exceptionhandling;

import br.com.veras.room_reservation.exception.CpfInvalidException;
import br.com.veras.room_reservation.exception.ErrorDetail;
import br.com.veras.room_reservation.exception.NotFoundUserException;
import br.com.veras.room_reservation.exception.UserCredentialExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandling extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<ErrorDetail> notFoundUserException(NotFoundUserException ex){
        ErrorDetail error = new ErrorDetail(HttpStatus.NOT_FOUND, "Esse Usuário não existe!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UserCredentialExistsException.class)
    public ResponseEntity<ErrorDetail> userCredentialExistsException(UserCredentialExistsException user){
        ErrorDetail erro = new ErrorDetail(HttpStatus.FOUND,"Esse Login ou senha já existe!");
        return ResponseEntity.status(HttpStatus.FOUND).body(erro);
    }

    @ExceptionHandler(CpfInvalidException.class)
    public ResponseEntity<ErrorDetail> CpfException(CpfInvalidException cpf){
        ErrorDetail erro = new ErrorDetail(HttpStatus.BAD_REQUEST,"Cpf inválido! Deve conter 11 caracteres, composto só por números" );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

}
