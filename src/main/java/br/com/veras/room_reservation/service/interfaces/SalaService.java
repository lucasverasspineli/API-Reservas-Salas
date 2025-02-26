package br.com.veras.room_reservation.service.interfaces;

import br.com.veras.room_reservation.dto.SalaDTO;

import java.util.List;

public interface SalaService {

    public SalaDTO criar(SalaDTO salaDTO);
    public SalaDTO editar(SalaDTO salaDTO, Long id);
    public void excluir(Long id);
    public SalaDTO buscarSala(Long id);
    public List<SalaDTO> todasSalas();
    public List<SalaDTO> semReservas();

}
