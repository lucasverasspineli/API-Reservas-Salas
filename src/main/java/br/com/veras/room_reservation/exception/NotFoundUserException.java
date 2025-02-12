package br.com.veras.room_reservation.exception;

public class NotFoundUserException extends RuntimeException {

    public NotFoundUserException(){
        super("Usuário não encontrado");
    }
    public NotFoundUserException(String msg){
        super(msg);
    }
}
