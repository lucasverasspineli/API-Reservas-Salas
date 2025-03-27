package br.com.veras.room_reservation.service.impl;

import br.com.veras.room_reservation.dto.ReservaDTO;
import br.com.veras.room_reservation.dto.SalaDTO;
import br.com.veras.room_reservation.dto.UserDTO;
import br.com.veras.room_reservation.exception.RoomReservedException;
import br.com.veras.room_reservation.model.Reserva;
import br.com.veras.room_reservation.model.Sala;
import br.com.veras.room_reservation.model.User;
import br.com.veras.room_reservation.model.UserId;
import br.com.veras.room_reservation.repository.ReservaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservaServiceImpl {

    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private SalaServiceImpl salaService;
    @Autowired
    private UserServiceImpl userService;

    public ReservaDTO criar(String login, String senha, Long id_sala) {
        /*Procurar pela Sala se ela já está reservada */
        UserDTO userDTO = userService.buscar(login, senha);
        SalaDTO buscarSala = salaService.buscarSala(id_sala);
        if (buscarSala.isStatusReservado()) {
            throw new RoomReservedException();
        }
        buscarSala.setStatusReservado(true);
        Reserva reservar = new Reserva();
        reservar.setUser(userDtoForUser(userDTO));
        reservar.setSala(salaDtoForSala(buscarSala));
        reservar.setDataHora(LocalDateTime.now());
        Reserva reserva = reservaRepository.save(reservar);

        SalaDTO salaDTO = salaService.editar(buscarSala, id_sala);
        reservaRepository.save(reserva);
        ReservaDTO reservaDTO = new ReservaDTO(reserva);
        return reservaDTO;
    }


    private Sala salaDtoForSala(SalaDTO salaDTO) {
        Sala sala = new Sala();
        BeanUtils.copyProperties(salaDTO, sala);
        return sala;
    }

    private User userDtoForUser(UserDTO userDTO) {
        User user = new User();
        user.setUserId(new UserId(userDTO.getLogin(), userDTO.getSenha()));
        user.setNome(userDTO.getNome());
        user.setSexo(userDTO.getSexo());
        user.setCpf(userDTO.getCpf());
        user.setPerfil(userDTO.getPerfil());
        user.setInstituicao(userDTO.getInstituicao());
        return user;
    }


}
