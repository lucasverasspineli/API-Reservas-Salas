package br.com.veras.room_reservation.dto;

import br.com.veras.room_reservation.model.Reserva;

import java.time.LocalDateTime;

public class ReservaDTO {

    private Long numeroReserva;
    private LocalDateTime dataHora;
    private UserMinDTO user;
    private SalaMinDTO sala;

    public ReservaDTO(){}

    public ReservaDTO(Reserva r) {
        this.dataHora = r.getDataHora();
        this.numeroReserva = r.getNumeroReserva();
        this.sala = new SalaMinDTO(r.getSala());
        this.user = new UserMinDTO(r.getUser());

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

    public void setNumeroReserva(Long numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public SalaMinDTO getSalaMin() {
        return sala;
    }

    public void setSalaMin(SalaMinDTO sala) {
        this.sala = sala;
    }

    public UserMinDTO getUserMin() {
        return user;
    }

    public void setUserMin(UserMinDTO user) {
        this.user = user;
    }

}
