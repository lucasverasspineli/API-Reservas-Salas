package br.com.veras.room_reservation.service.impl;

import br.com.veras.room_reservation.dto.SalaDTO;
import br.com.veras.room_reservation.exception.NotFoundSalaException;
import br.com.veras.room_reservation.model.Sala;
import br.com.veras.room_reservation.repository.SalaRepository;
import br.com.veras.room_reservation.service.interfaces.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalaServiceImpl implements SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Transactional(readOnly = true)
    public List<SalaDTO> todasSalas() {
        List<Sala> list = salaRepository.findAll();
        return list.stream().map(s -> new SalaDTO(s)).collect(Collectors.toList());
    }

    public SalaDTO criar(SalaDTO salaDTO) {
        Sala sala = new Sala();
        sala.setStatusReservado(false);//Toda sala criada fica disponível para reservar!
        sala.setRecursos(salaDTO.getRecursos());
        sala.setEstado(salaDTO.getEstado());
        sala.setCapacidade(salaDTO.getCapacidade());
        sala.setTipo(salaDTO.getTipo());
        sala = salaRepository.save(sala);
        salaDTO.setId(sala.getId());
        salaDTO.setStatusReservado(sala.isStatusReservado());
        return salaDTO;
    }

    @Transactional(readOnly = true)
    public SalaDTO buscarSala(Long id) {
        Optional<Sala> sala = salaRepository.findById(id);
        if (!sala.isPresent()) {
            throw new NotFoundSalaException();
        }
        SalaDTO salaDTO = new SalaDTO(sala.get());
        return salaDTO;
    }

    public SalaDTO editar(SalaDTO salaDTO, Long id) {
        SalaDTO editSala = buscarSala(id);
        Sala sala = editSala.salaDtoForSala();
        sala.setStatusReservado(salaDTO.isStatusReservado());
        sala.setTipo(salaDTO.getTipo());
        sala.setCapacidade(salaDTO.getCapacidade());
        sala.setRecursos(salaDTO.getRecursos());
        sala.setEstado(salaDTO.getEstado());
        salaRepository.save(sala);
        editSala = new SalaDTO(sala);
        return editSala;
    }

    public void excluir(Long id) {
        Optional<Sala> opDel = salaRepository.findById(id);
        if (!opDel.isPresent()) {
            throw new NotFoundSalaException();
        }
        salaRepository.delete(opDel.get());
    }

    @Transactional(readOnly = true)
    public List<SalaDTO> semReservas() {
        List<Sala> list = salaRepository.findByStatusReservadoFalse();
        return list.stream().map(s -> new SalaDTO(s)).collect(Collectors.toList());
    }

}
