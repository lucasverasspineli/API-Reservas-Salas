package br.com.veras.room_reservation.dto;

import br.com.veras.room_reservation.model.Reserva;
import br.com.veras.room_reservation.model.Sala;
import br.com.veras.room_reservation.model.User;
import br.com.veras.room_reservation.model.UserId;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Objects;

public class ReservaDTO {

    private Long numeroReserva;
    private LocalDateTime dataHora;
    private User user;
    private Sala sala;

    public ReservaDTO(){}

    public ReservaDTO(LocalDateTime dataHora, Long numeroReserva, Sala sala, User user) {
        this.dataHora = dataHora;
        this.numeroReserva = numeroReserva;
        this.sala = sala;
        this.user = user;
    }

    public ReservaDTO(Reserva entity){
        BeanUtils.copyProperties(entity,this);
    }

    public void ReservaDtoForReserva(LocalDateTime dataHora, Long id_sala, String login, String senha){
        this.dataHora = dataHora;
        sala.setId(id_sala);
        user.setUserId(new UserId(login,senha));
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ReservaDTO that)) return false;
        return Objects.equals(numeroReserva, that.numeroReserva);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroReserva);
    }
}
