package br.com.veras.room_reservation.service;

import br.com.veras.room_reservation.model.Sala;
import br.com.veras.room_reservation.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;


    public Sala criarSala(Sala sala){
        Sala sa = sala;
        sa.setStatus_reservado(false);
        return salaRepository.save(sa);
    }


}
