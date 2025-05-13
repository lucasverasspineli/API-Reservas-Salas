package br.com.veras.room_reservation.service.impl;

import br.com.veras.room_reservation.dto.*;
import br.com.veras.room_reservation.exception.CpfInvalidException;
import br.com.veras.room_reservation.exception.NotFoundUserException;
import br.com.veras.room_reservation.exception.UserCredentialExistsException;
import br.com.veras.room_reservation.model.Reserva;
import br.com.veras.room_reservation.model.Sala;
import br.com.veras.room_reservation.model.User;
import br.com.veras.room_reservation.model.UserId;
import br.com.veras.room_reservation.repository.UserRepository;
import br.com.veras.room_reservation.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public UserDTO buscar(String login, String senha) {
        User user = userRepository.findById(new UserId(login, senha)).orElseThrow(NotFoundUserException::new);
        return new UserDTO(user);
    }

    @Transactional
    @Override
    public UserDTO criar(UserDTO userDTO, String senha) {
        validationLoginPassword(userDTO.getLogin(), senha);
        User user = new User();
        validationCpf(userDTO.getCpf());
        user.setUserId(new UserId(userDTO.getLogin(), senha));
        convertUserDtoForUser(userDTO, user, senha);
        return new UserDTO(userRepository.save(user));
    }

    private void validationCpf(String cpf){
        if(cpf.length() != 11 ){
            throw new CpfInvalidException();
        } else if(!cpf.matches("[0-9]+")){
            throw new CpfInvalidException();
        }
    }

    private void validationLoginPassword(String login, String senha) {
        Optional<User> opValid = userRepository.findById(new UserId(login, senha));
        if (opValid.isPresent()) {
            throw new UserCredentialExistsException();
        }
    }

    private void convertUserDtoForUser(UserDTO userDTO, User user, String senha) {
        user.setUserId(((!userDTO.getLogin().equals(user.getUserId().getLogin()))
                || (senha.equals(user.getUserId().getSenha()))) ?
                new UserId(userDTO.getLogin(), senha) :
                new UserId(user.getUserId().getLogin(), user.getUserId().getSenha()));

        user.setNome((userDTO.getNome() == null) ? user.getNome() : userDTO.getNome());
        user.setSexo((userDTO.getSexo() == null) ? user.getSexo() : userDTO.getSexo());
        user.setCpf((userDTO.getCpf() == null) ? user.getCpf() : userDTO.getCpf());
        user.setPerfil((userDTO.getPerfil() == null) ? user.getPerfil() : userDTO.getPerfil());
        user.setInstituicao((userDTO.getInstituicao() == null) ? user.getInstituicao() : userDTO.getInstituicao());
    }

    @Transactional
    @Override
    public UserDTO editar(UserDTO userDTO, String senha) {
        User editUser = userRepository.findById(new UserId(userDTO.getLogin(), senha)).orElseThrow(NotFoundUserException::new);
        validationCpf(userDTO.getCpf());
        convertUserDtoForUser(userDTO, editUser, senha);
        return new UserDTO(userRepository.save(editUser));
    }

    @Transactional
    public void editarListReservasUser(UserDTO userDTO, String senha, Long idsala) {
        User editUser = userRepository.findById(new UserId(userDTO.getLogin(), senha)).orElseThrow(NotFoundUserException::new);
        convertUserDtoForUser(userDTO, editUser, senha);
        /*métdo para validar a lista de reservas no banco!*/
        if (userDTO.getReservas() != null || !userDTO.getReservas().isEmpty()) {
            listarReservasUser(userDTO.getReservas(), idsala, editUser);
        }
        userRepository.save(editUser);
    }

    @Transactional
    public void excluir(String login, String senha) {
        User user = userRepository.findById(new UserId(login, senha)).orElseThrow(NotFoundUserException::new);
        userRepository.delete(user);
    }

    protected void listarReservasUser(List<ReservaMinDTO> listResDTO, Long idSala, User user) {
        user.getReservas().clear(); //Limpando a lista
        Sala sala = new Sala();
        sala.setId(idSala);
        for (ReservaMinDTO reserMin : listResDTO) {
            Reserva reserva = new Reserva();
            reserva.setNumeroReserva(reserMin.getNumeroReserva());
            reserva.setDataHora(reserMin.getDataHora());
            reserva.setSala(sala);
            reserva.setUser(user);
            user.getReservas().add(reserva);
        }
    }


}
