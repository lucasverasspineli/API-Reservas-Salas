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

    @Transactional
    public SalaDTO criar(SalaDTO salaDTO) {
        Sala sala = new Sala();
        salaDTO.setStatusReservado(false);//Toda sala criada fica disponível para reservar!
        convertSalaDtoForSala(salaDTO, sala);
        salaRepository.save(sala);
        return new SalaDTO(sala);
    }

    @Transactional(readOnly = true)
    public SalaDTO buscarSala(Long id) {
        Sala sala = salaRepository.findById(id).orElseThrow(NotFoundSalaException::new);
        return new SalaDTO(sala);
    }

    @Transactional
    public SalaDTO editar(SalaDTO salaDTO, Long id) {
        Sala sala = obterSala(id);
        convertSalaDtoForSala(salaDTO, sala);
        salaRepository.save(sala);
        return new SalaDTO(sala);
    }
    // Convertendo e fazendo a validação para dos campos da entidade Sala
    private void convertSalaDtoForSala(SalaDTO salaDTO, Sala sala) {
        sala.setCapacidade((salaDTO.getCapacidade() == null) ? sala.getCapacidade() : salaDTO.getCapacidade());
        sala.setRecursos((salaDTO.getRecursos() == null) ? sala.getRecursos() : salaDTO.getRecursos());
        sala.setEstado((salaDTO.getEstado() == null) ? sala.getEstado() : salaDTO.getEstado());
        sala.setTipo((salaDTO.getTipo() == null) ? sala.getTipo() : salaDTO.getTipo());
        sala.setStatusReservado((salaDTO.isStatusReservado() != sala.isStatusReservado()) ? salaDTO.isStatusReservado() : sala.isStatusReservado());
    }

    @Transactional(readOnly = true)
    private Sala obterSala(Long id) {
        Sala sala = salaRepository.findById(id).orElseThrow(NotFoundSalaException::new);
        return sala;
    }

    @Transactional
    public void excluir(Long id) {
        Sala sala = obterSala(id);
        salaRepository.delete(sala);
    }

    @Transactional(readOnly = true)
    public List<SalaDTO> semReservas() {
        List<Sala> list = salaRepository.findByStatusReservadoFalse();
        return list.stream().map(s -> new SalaDTO(s)).collect(Collectors.toList());
    }

}
