package br.com.veras.room_reservation.dto;

import br.com.veras.room_reservation.model.Sala;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SalaDTO {

    private Long id;
    private String tipo;
    private String estado;
    private Integer capacidade;
    private String recursos;
    private boolean statusReservado;
    private List<ReservaMinDTO> reservas = new ArrayList<>();

    public SalaDTO() {
    }

    public SalaDTO(Sala entity){
        this.id = entity.getId();
        this.tipo = entity.getTipo();
        this.estado = entity.getEstado();
        this.capacidade = entity.getCapacidade();
        this.recursos = entity.getRecursos();
        this.statusReservado = entity.isStatusReservado();
        if(entity.getReservas() != null){
            this.reservas = entity.getReservas()
                    .stream()
                    .map(ReservaMinDTO::new)
                    .collect(Collectors.toList());
        }
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

    public List<ReservaMinDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaMinDTO> reservas) {
        this.reservas = reservas;
    }

}
