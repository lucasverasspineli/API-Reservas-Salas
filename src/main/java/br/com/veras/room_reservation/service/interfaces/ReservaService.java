package br.com.veras.room_reservation.service.interfaces;

import br.com.veras.room_reservation.dto.ReservaDTO;
import br.com.veras.room_reservation.dto.SalaDTO;

import java.util.List;

public interface ReservaService {

    public ReservaDTO criar(SalaDTO salaDTO);
    public ReservaDTO editar(SalaDTO salaDTO, Long id);
    public void excluir(Long id);
    public ReservaDTO buscarSala(Long id);
    public List<ReservaDTO> salasReservadas();

}
