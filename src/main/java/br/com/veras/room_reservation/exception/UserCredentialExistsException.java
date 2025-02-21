package br.com.veras.room_reservation.exception;

public class UserCredentialExistsException extends RuntimeException {

    public UserCredentialExistsException(){
        super();
    }

    public UserCredentialExistsException(String message) {
        super(message);
    }
}
