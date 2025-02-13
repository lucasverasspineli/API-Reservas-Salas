package br.com.veras.room_reservation.service;

import br.com.veras.room_reservation.exception.NotFoundSalaException;
import br.com.veras.room_reservation.model.Sala;
import br.com.veras.room_reservation.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;


    public Sala criarSala(Sala sala){
        Sala sa = sala;
        sa.setStatus_reservado(false);
        return salaRepository.save(sa);
    }

    @Transactional(readOnly = true)
    public Sala buscarSala(Long id){
        Optional<Sala> sala = salaRepository.findById(id);
        if(!sala.isPresent()){
            throw new NotFoundSalaException();
        }
        return sala.get();
    }

    public Sala editar(Long id, Sala sala) {
        Sala editSala = buscarSala(id);
        editSala.setStatus_reservado(true);
        editSala.setCapacidade(sala.getCapacidade());
        editSala.setRecursos(sala.getRecursos());
        editSala.setTipo(sala.getTipo());
        editSala.setEstado(sala.getEstado());
        return salaRepository.save(editSala);

    }


}
