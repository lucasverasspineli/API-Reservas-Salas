package br.com.veras.room_reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetail {

    private HttpStatus httpStatus;
    private String msg;

}
