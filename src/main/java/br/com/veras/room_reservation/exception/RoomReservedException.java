package br.com.veras.room_reservation.exception;

public class RoomReservedException extends RuntimeException{

    public RoomReservedException(){ super();}

    public RoomReservedException(String mensg){
        super(mensg);
    }

}
