package br.com.veras.room_reservation.service;

import br.com.veras.room_reservation.exception.NotFoundSalaException;
import br.com.veras.room_reservation.exception.NotFoundUserException;
import br.com.veras.room_reservation.model.Sala;
import br.com.veras.room_reservation.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Transactional(readOnly = true)
    public List<Sala> listagem(){
        List<Sala> list = salaRepository.findAll();
        return list;
    }

    public Sala criarSala(Sala sala){
        Sala sa = sala;
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
        editSala.setStatusReservado((sala.isStatusReservado())? true : false);
        editSala.setCapacidade(sala.getCapacidade());
        editSala.setRecursos(sala.getRecursos());
        editSala.setTipo(sala.getTipo());
        editSala.setEstado(sala.getEstado());
        return salaRepository.save(editSala);
    }

    public void excluir(Long id){
        Optional<Sala> opDel = salaRepository.findById(id);
        if(!opDel.isPresent()){
            throw new NotFoundSalaException();
        }
        salaRepository.delete(opDel.get());
    }
    @Transactional(readOnly = true)
    public List<Sala> semReservas(){
        List<Sala> list = salaRepository.findByStatusReservadoFalse();
        return list;
    }

}
