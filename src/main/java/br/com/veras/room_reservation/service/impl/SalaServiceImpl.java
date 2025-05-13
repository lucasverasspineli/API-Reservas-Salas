package br.com.veras.room_reservation.service.impl;

import br.com.veras.room_reservation.dto.ReservaMinDTO;
import br.com.veras.room_reservation.dto.SalaDTO;
import br.com.veras.room_reservation.exception.NotFoundSalaException;
import br.com.veras.room_reservation.model.Reserva;
import br.com.veras.room_reservation.model.Sala;
import br.com.veras.room_reservation.model.User;
import br.com.veras.room_reservation.model.UserId;
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
        return list.stream().map(SalaDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public SalaDTO criar(SalaDTO salaDTO) {
        Sala sala = new Sala();
        salaDTO.setStatusReservado(false);//Toda sala criada fica disponível para reservar!
        convertSalaDtoForSala(salaDTO, sala);
        return new SalaDTO(salaRepository.save(sala));
    }

    @Transactional(readOnly = true)
    public SalaDTO buscarSala(Long id) {
        Sala sala = salaRepository.buscarSalaReservas(id).orElseThrow(NotFoundSalaException::new);
        return new SalaDTO(sala);
    }

    @Transactional
    public SalaDTO editar(SalaDTO salaDTO, Long id) {
        Sala sala = obterSala(id);
        convertSalaDtoForSala(salaDTO, sala);
        return new SalaDTO(salaRepository.save(sala));
    }

    // Convertendo e fazendo a validação para os campos da entidade Sala
    private void convertSalaDtoForSala(SalaDTO salaDTO, Sala sala) {
        sala.setCapacidade((salaDTO.getCapacidade() == null) ? sala.getCapacidade() : salaDTO.getCapacidade());
        sala.setRecursos((salaDTO.getRecursos() == null) ? sala.getRecursos() : salaDTO.getRecursos());
        sala.setEstado((salaDTO.getEstado() == null) ? sala.getEstado() : salaDTO.getEstado());
        sala.setTipo((salaDTO.getTipo() == null) ? sala.getTipo() : salaDTO.getTipo());
        sala.setStatusReservado((salaDTO.isStatusReservado() != sala.isStatusReservado()) ? salaDTO.isStatusReservado() : sala.isStatusReservado());
    }

    private void updateReservation(Sala sala, List<ReservaMinDTO> listR, String login, String senha){
        sala.getReservas().clear();
        User user = new User();
        user.setUserId(new UserId(login,senha));
        for(ReservaMinDTO reserMin : listR){
            Reserva reserva = new Reserva();
            reserva.setNumeroReserva(reserMin.getNumeroReserva());
            reserva.setDataHora(reserMin.getDataHora());
            reserva.setSala(sala);
            reserva.setUser(user);
            sala.getReservas().add(reserva);
        }
    }

    protected void editListReservaSala(SalaDTO salaDTO, String login, String senha) {
        Sala sala = obterSala(salaDTO.getId());
        if(salaDTO.isStatusReservado()){
            sala.setStatusReservado(salaDTO.isStatusReservado());
        }
        if(salaDTO.getReservas() != null || !salaDTO.getReservas().isEmpty()){
            updateReservation(sala, salaDTO.getReservas(),login, senha);
        }
        salaRepository.save(sala);
    }

    @Transactional(readOnly = true)
    public Sala obterSala(Long id) {
        return salaRepository.findById(id).orElseThrow(NotFoundSalaException::new);
    }

    @Transactional
    public void excluir(Long id) {
        Sala sala = obterSala(id);
        salaRepository.delete(sala);
    }

    @Transactional(readOnly = true)
    public List<SalaDTO> semReservas() {
        List<Sala> list = salaRepository.findByStatusReservadoFalse();
        return list.stream().map(SalaDTO::new).collect(Collectors.toList());
    }

    public List<Reserva> listarResrvas(Long id){
        Optional<Sala> op =salaRepository.findById(id);

        return op.get().getReservas();
    }

}
