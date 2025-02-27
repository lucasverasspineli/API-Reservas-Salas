package br.com.veras.room_reservation.dto;

import br.com.veras.room_reservation.model.Sala;
import jakarta.persistence.Column;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class SalaDTO {

    private Long id;
    private String tipo;
    private String estado;
    private Integer capacidade;
    private String recursos;
    private boolean statusReservado;

    public Sala salaDtoForSala() {
        Sala sala = new Sala();
        sala.setId(this.getId());
        sala.setCapacidade(this.getCapacidade());
        sala.setRecursos(this.getRecursos());
        sala.setEstado(this.getEstado());
        sala.setTipo(this.getTipo());
        sala.setStatusReservado(this.isStatusReservado());
        return sala;
    }

    public SalaDTO() {
    }

    public SalaDTO(Sala entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public boolean isStatusReservado() {
        return statusReservado;
    }

    public void setStatusReservado(boolean statusReservado) {
        this.statusReservado = statusReservado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SalaDTO salaDTO)) return false;
        return Objects.equals(id, salaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
