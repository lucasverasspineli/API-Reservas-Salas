package br.com.veras.room_reservation.exception;

public class CpfInvalidException extends RuntimeException {

    public CpfInvalidException() {
    }

    public CpfInvalidException(String message) {
        super(message);
    }


}
