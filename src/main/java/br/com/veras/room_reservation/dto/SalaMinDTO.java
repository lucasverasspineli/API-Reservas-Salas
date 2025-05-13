package br.com.veras.room_reservation.dto;

import br.com.veras.room_reservation.model.Sala;

public class SalaMinDTO {

    private Long id;
    private String tipoSala;

    public SalaMinDTO() {
    }

    public SalaMinDTO(Sala entity) {
        this.id = entity.getId();
        this.tipoSala = entity.getTipo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }
}
