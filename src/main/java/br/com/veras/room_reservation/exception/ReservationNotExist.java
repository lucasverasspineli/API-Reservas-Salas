package br.com.veras.room_reservation.exception;

public class ReservationNotExist extends RuntimeException{

    public ReservationNotExist() {
    }

    public ReservationNotExist(String message) {
        super(message);
    }
}
