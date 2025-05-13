package br.com.veras.room_reservation.dto;

import br.com.veras.room_reservation.model.Reserva;

import java.time.LocalDateTime;

public class ReservaMinDTO {

    private Long numeroReserva;
    private LocalDateTime dataHora;

    public ReservaMinDTO(){}

    public ReservaMinDTO(Reserva entity){
        this.numeroReserva = entity.getNumeroReserva();
        this.dataHora = entity.getDataHora();
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getNumeroReserva() {
        return numeroReserva;
    }


}
