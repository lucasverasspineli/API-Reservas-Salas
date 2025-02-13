package br.com.veras.room_reservation.exception;

public class NotFoundSalaException extends RuntimeException{

    public NotFoundSalaException(){
        super("A sala não foi encontrada");
    }

    public NotFoundSalaException(String msg){
        super(msg);
    }

}
